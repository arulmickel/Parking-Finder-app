package com.arul.parkingfinder.data.api

import com.arul.parkingfinder.util.RetryInterceptor
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    // TODO: change this to your real host (can be GitHub Pages or any HTTPS)
    private const val BASE_URL = "https://example.com/api/"

    private val logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
    private val client = OkHttpClient.Builder()
        .addInterceptor(RetryInterceptor(3))
        .addInterceptor(logging)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
        .build()

    val api: ParkingApi = retrofit.create(ParkingApi::class.java)
}
