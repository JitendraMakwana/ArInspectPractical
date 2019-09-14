package com.app.arinspect.arinspectpracticaldemo.apiService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Jitendra Makwana
 * @created on 15-Sept-19.
 */
public class ApiClient {
    var BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"
    var retrofit: Retrofit? = null

    fun getApiClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit
    }
}