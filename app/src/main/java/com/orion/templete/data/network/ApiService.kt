package com.orion.templete.data.network


import com.orion.templete.data.model.ContactsDTO
import retrofit2.http.GET

interface ApiService {
    @GET("contacts")
    suspend fun getContacts(
    ): retrofit2.Response<ContactsDTO>
    companion object{
        var baseurl = "http://10.0.2.2:8080/"
    }
}