package com.orion.templete.domain.repository

import com.orion.templete.data.model.ArtWorkDTO

interface GetArtworkRepository {
    suspend fun getArtwork(): ArtWorkDTO
}