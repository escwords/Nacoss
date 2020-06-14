package com.words.nacoss.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.words.nacoss.util.url
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface StaffService {

    @GET("nacoss.json")
    suspend fun getStaffsAsync(): NetworkContainer
}

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

//Main entry in network access
object StaffWebService{

   private val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    val staffWebService = retrofit.create(StaffService::class.java)
}