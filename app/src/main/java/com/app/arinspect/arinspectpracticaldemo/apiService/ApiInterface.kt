package com.app.arinspect.arinspectpracticaldemo.apiService

import com.app.arinspect.arinspectpracticaldemo.dataModel.JsonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @author Jitendra Makwana
 * @created on 15-Sept-19.
 */
public interface ApiInterface {

    @GET("facts.json")
    fun getJsonRowData() : Call<JsonResponse>
}