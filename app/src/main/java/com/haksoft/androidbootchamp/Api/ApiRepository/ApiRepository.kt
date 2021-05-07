package com.haksoft.androidbootchamp.Api.ApiRepository

import com.haksoft.androidbootchamp.Api.Model.Currency
import com.haksoft.androidbootchamp.Api.RetrofitInstance
import retrofit2.Call

class ApiRepository {
    private val api by lazy { RetrofitInstance.api }
    fun getConverted(base : String): Call<Currency>{
        return api.getCorverted(base)
    }

}