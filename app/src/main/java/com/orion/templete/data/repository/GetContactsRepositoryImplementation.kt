package com.orion.templete.data.repository

import com.orion.templete.data.model.ContactsDTO
import com.orion.templete.data.network.ApiService
import com.orion.templete.domain.repository.GetContactsRepository
import com.orion.templete.util.SafeApiRequest
import javax.inject.Inject

class GetContactsRepositoryImplementation @Inject constructor(private  val apiService: ApiService):
    GetContactsRepository, SafeApiRequest() {
    override suspend fun getContacts(): ContactsDTO {
        val response = safeApiRequest { apiService.getContacts() }
        return response
    }
}