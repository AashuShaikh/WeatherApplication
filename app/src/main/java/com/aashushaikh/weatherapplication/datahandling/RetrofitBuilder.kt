package com.aashushaikh.weatherapplication.datahandling

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Not used because of Dagger
class RetrofitBuilder {
    companion object{
        const val BASE_URL = "https://api.weatherapi.com"
        var retrofitServices: RetrofitServices? = null
        fun getInstance(): RetrofitServices{
            if(retrofitServices == null){
                return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RetrofitServices::class.java)
            }
            return retrofitServices!!
        }
    }
}