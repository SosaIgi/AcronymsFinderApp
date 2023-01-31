package com.tes.apps.development.acronymsfinderapp.di

import android.icu.text.LocaleDisplayNames.DialectHandling
import com.google.gson.Gson
import com.tes.apps.development.acronymsfinderapp.data.remote.AcronymApiService
import com.tes.apps.development.acronymsfinderapp.data.repository.AcronymRepository
import com.tes.apps.development.acronymsfinderapp.data.repository.AcronymRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)

class AppModule {

    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .build()

    @Provides
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(AcronymApiService.BASE_URL)
        .build()

    @Provides
    fun providesShowsApi(retrofit: Retrofit): AcronymApiService = retrofit.create(AcronymApiService::class.java)

    @Provides
    fun providesRepository(
        api: AcronymApiService,
    ): AcronymRepository {
        return AcronymRepositoryImpl(api)
    }

    @Provides
    fun providesMainDispatcher():CoroutineDispatcher=Dispatchers.Main
}