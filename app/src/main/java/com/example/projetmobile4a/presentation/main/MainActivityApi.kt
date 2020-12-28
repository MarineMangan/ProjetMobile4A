package com.example.projetmobile4a.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetmobile4a.api.CountriesApi
import com.example.projetmobile4a.R
import com.example.projetmobile4a.adapter.ListAdapter
import com.example.projetmobile4a.model.Countries
import com.example.projetmobile4a.model.RestCountriesResponse
import kotlinx.android.synthetic.main.activity_main_api.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityApi : AppCompatActivity() {

    val Countries: MutableList<Countries> = ArrayList()
    var recyclerView: RecyclerView? = null
    var mAdapter: ListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_api)

        activity_main_api.layoutManager = LinearLayoutManager(this)
        //  activity_main3.adapter = ListAdapter(Pokemon, this)
        // mAdapter = ListAdapter(Pokemon,this)
        makeApiCall()
    }

    fun ShowCountries(listCountries: MutableList<Countries>?) {
        mAdapter = ListAdapter(listCountries ,this)
        activity_main_api?.adapter = mAdapter;

    }

    fun makeApiCall() {
        val BASE_URL: String = "https://restcountries.eu/"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CountriesApi::class.java)
        val Countries = service.getCountriesResponse()
        Countries.enqueue(object : Callback<RestCountriesResponse> {
            override fun onFailure(call: Call<RestCountriesResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<RestCountriesResponse>, response: Response<RestCountriesResponse>
            ) {

                var countriesList: MutableList<Countries>? = response.body()?.getResults()
                ShowCountries(countriesList)
            }

        })


    }

}