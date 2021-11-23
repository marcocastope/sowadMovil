package com.marcocastope.sowadmovil.data.networking

import com.marcocastope.sowadmovil.data.networking.RemoteApi.Companion.API_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


fun buildOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

fun buildRetrofit(): Retrofit =
    Retrofit.Builder()
        .client(buildOkHttpClient())
        .baseUrl(API_URL)
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .build()

fun buildApiService(): RemoteApiService =
    buildRetrofit().create(RemoteApiService::class.java)