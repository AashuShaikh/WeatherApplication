package com.aashushaikh.weatherapplication.datahandling

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aashushaikh.weatherapplication.datamodel.WeatherResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val repo: Repo
): ViewModel() {

    val weatherDetail = MutableLiveData<WeatherResponseModel>()
    val isLoading = MutableLiveData<Boolean>(false)

    fun getWeatherDetail(city: String){
        viewModelScope.launch(Dispatchers.IO){
            isLoading.postValue(true)
            val response = repo.getWeatherDetail(city)
            if(response.isSuccessful){
                weatherDetail.postValue(response.body())
                isLoading.postValue(false)
            }
        }
    }

}