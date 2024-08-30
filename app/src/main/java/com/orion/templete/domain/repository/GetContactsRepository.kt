package com.orion.templete.domain.repository

import com.orion.templete.data.model.ContactsDTO

interface GetContactsRepository {
    suspend fun getContacts(): ContactsDTO
}