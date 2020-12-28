package com.example.projetmobile4a.api

import com.example.projetmobile4a.model.RestCountriesResponse
import retrofit2.Call
import retrofit2.http.GET

interface CountriesApi {

    @GET("/rest/v2/all")
    fun getCountriesResponse (): Call<RestCountriesResponse>


}