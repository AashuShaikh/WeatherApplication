package com.aashushaikh.weatherapplication.datahandling

import com.aashushaikh.weatherapplication.BuildConfig
import com.aashushaikh.weatherapplication.datamodel.WeatherResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {

    @GET("/v1/current.json")
    suspend fun getWeatherDetail(
        @Query("key") apiKey: String = BuildConfig.WEATHER_API_KEY,
        @Query("q") city: String
    ): Response<WeatherResponseModel>

}

//http://api.weatherapi.com/v1/current.json?key=e0cf7c1513444998a86163558241307&q=Mumbai&aqi=no