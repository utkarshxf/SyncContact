package com.orion.templete.presentation.syncscreen

import android.content.ContentProviderOperation
import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orion.templete.data.model.Contacts
import com.orion.templete.data.model.ContactsDTO
import com.orion.templete.domain.repository.GetContactsRepository
import com.orion.templete.util.ContactScreenState
import com.orion.templete.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SyncScreenViewModel @Inject constructor(
    private val getContactsRepository: GetContactsRepository,
    private val context: Context
) : ViewModel() {
    val contactData = mutableStateOf(ContactScreenState())
    val syncProgress = mutableStateOf(0)

    fun fetchContacts() {
        flow {
            emit(Resource.Loading(null))
            try {
                val contacts = getContactsRepository.getContacts()
                emit(Resource.Success(contacts))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "An unexpected error occurred"))
            }
        }
            .onEach {
                when (it) {
                    is Resource.Loading -> {
                        contactData.value = ContactScreenState(isLoading = true)
                    }

                    is Resource.Success -> {
                        val contacts = it.data ?: ContactsDTO()
                        syncContacts(context , contacts)
                    }

                    is Resource.Error -> {
                        contactData.value = ContactScreenState(error = it.message.toString())
                    }
                }
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    private fun syncContacts(context: Context,contacts: ContactsDTO) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                contacts.forEachIndexed { index, contact ->
                    addContact(context, contact.firstName, contact.lastName, contact.phoneNumber)
                    syncProgress.value = (index + 1) * 100 / contacts.size
                }
                contactData.value = ContactScreenState(data = contacts, isLoading = false)
            } catch (e: Exception) {
                contactData.value = ContactScreenState(error = e.localizedMessage ?: "An unexpected error occurred")
            }
        }
    }

    private fun addContact(
        context: Context,
        firstName: String,
        lastName: String,
        phoneNumber: String
    ) {
        val operationList = arrayListOf<ContentProviderOperation>().apply {
            add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build())

            add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, firstName)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME, lastName)
                .build())

            add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
                .build())
        }

        try {
            context.contentResolver.applyBatch(ContactsContract.AUTHORITY, operationList)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
