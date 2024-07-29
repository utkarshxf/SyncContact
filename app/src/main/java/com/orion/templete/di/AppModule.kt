package com.orion.templete.di

import android.content.Context
import com.orion.templete.data.network.ApiService
import com.orion.templete.data.network.ApiService.Companion.baseurl
import com.orion.templete.data.repository.GetArtworkRepositoryImplementation
import com.orion.templete.domain.repository.GetArtworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  AppModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder().baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    @Provides
    fun provideRepository(apiService: ApiService):GetArtworkRepository{
        return GetArtworkRepositoryImplementation(apiService = apiService)
    }

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

}