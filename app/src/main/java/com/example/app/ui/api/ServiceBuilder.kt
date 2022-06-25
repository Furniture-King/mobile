package com.example.app.ui.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// The service builder
val USE_API = ServiceBuilder.buildService(ApiService::class.java)

/**
 * Object that allow to call the API
 */
object ServiceBuilder {
    private const val URL ="https://furniturekings-app.herokuapp.com/"
    // Create a http client
    private val okHttp = OkHttpClient.Builder()

    // Retrofit builder
    private val builder =Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    // Create retrofit Instance
    private val retrofit = builder.build()

    fun <T> buildService (serviceType :Class<T>):T{
        return retrofit.create(serviceType)
    }

}