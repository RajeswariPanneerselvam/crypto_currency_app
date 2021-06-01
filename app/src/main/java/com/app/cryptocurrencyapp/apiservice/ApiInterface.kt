package com.app.cryptocurrencyapp.apiservice

import com.app.cryptocurrencyapp.model.CoinsModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("assets")
    fun getCoinsList(): Call<CoinsModel>
}