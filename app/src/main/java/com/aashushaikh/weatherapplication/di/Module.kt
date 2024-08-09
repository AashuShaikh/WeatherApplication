package com.aashushaikh.weatherapplication.di

import com.aashushaikh.weatherapplication.datahandling.Repo
import com.aashushaikh.weatherapplication.datahandling.RetrofitBuilder
import com.aashushaikh.weatherapplication.datahandling.RetrofitServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class Module {

    companion object{
        const val BASE_URL = "https://api.weatherapi.com"
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitServices(retrofit: Retrofit): RetrofitServices{
        return retrofit.create(RetrofitServices::class.java)
    }

    @Singleton
    @Provides
    fun provideRepo(retrofitServices: RetrofitServices): Repo{
        return Repo(retrofitServices)
    }

}