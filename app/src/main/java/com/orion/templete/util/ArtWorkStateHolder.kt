package com.orion.templete.util

import com.orion.templete.data.model.ArtWorkDTO

data class ArtWorkStateHolder(
    val isLoading: Boolean = false,
    val data: ArtWorkDTO? = null,
    val error: String = ""
)