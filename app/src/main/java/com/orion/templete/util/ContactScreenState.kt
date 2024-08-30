package com.orion.templete.util

import com.orion.templete.data.model.ContactsDTO

data class ContactScreenState(
    val isLoading: Boolean = false,
    val data: ContactsDTO? = null,
    val error: String? = null
)