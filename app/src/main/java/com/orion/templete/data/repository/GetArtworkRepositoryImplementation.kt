package com.orion.templete.data.repository

import com.orion.templete.data.model.ArtWorkDTO
import com.orion.templete.data.network.ApiService
import com.orion.templete.domain.repository.GetArtworkRepository
import com.orion.templete.util.SafeApiRequest
import javax.inject.Inject

class GetArtworkRepositoryImplementation @Inject constructor(private  val apiService: ApiService):
    GetArtworkRepository, SafeApiRequest() {
    override suspend fun getArtwork(): ArtWorkDTO {
        val response = safeApiRequest { apiService.getAllArtworks() }
        return response
    }
}