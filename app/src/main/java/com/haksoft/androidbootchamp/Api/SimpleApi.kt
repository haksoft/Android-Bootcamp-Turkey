package com.haksoft.androidbootchamp.Api

import com.haksoft.androidbootchamp.Api.Model.Currency
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SimpleApi {

    @GET("/v6/c08f97e5adc30e2625057122/latest/{base_code}")
    fun getCorverted(@Path("base_code") base_code :String):Call<Currency>

}