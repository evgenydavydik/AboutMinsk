package com.davydikes.aboutminsk.cloud

import com.davydikes.aboutminsk.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.Duration
import java.util.concurrent.TimeUnit

interface CloudKrokappApi {

    @GET("get_languages")
    suspend fun importLanguages(): Response<List<LanguagesCloutResult>>

    @GET("get_points/11/")
    suspend fun importPlaces(): Response<List<PlacesCloutResult>>

    companion object {

        private const val API_URL = "https://krokapp.by/api/"

        fun get(): CloudKrokappApi =
            Retrofit.Builder().baseUrl(API_URL).addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder().apply {
                        if (BuildConfig.DEBUG) {
                            addInterceptor(
                                HttpLoggingInterceptor()
                                    .setLevel(HttpLoggingInterceptor.Level.BODY)
                            )
                        }
                    }.connectTimeout(100, TimeUnit.SECONDS).readTimeout(100, TimeUnit.SECONDS)
                        .build()
                ).build().create(CloudKrokappApi::class.java)
    }
}