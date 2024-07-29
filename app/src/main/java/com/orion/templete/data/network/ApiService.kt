package com.orion.templete.data.network


import com.orion.templete.data.model.ArtWorkDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    //    after ? everything represent query
    @GET("artwork")
    suspend fun getAllArtworks(
    ): retrofit2.Response<ArtWorkDTO>



    companion object{
        var baseurl = "http://10.0.2.2:8080/"
    }

}