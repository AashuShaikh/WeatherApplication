package com.aashushaikh.weatherapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.aashushaikh.weatherapplication.databinding.ActivityMainBinding
import com.aashushaikh.weatherapplication.datahandling.Repo
import com.aashushaikh.weatherapplication.datahandling.RetrofitBuilder
import com.aashushaikh.weatherapplication.datahandling.WeatherViewModel
import com.aashushaikh.weatherapplication.datahandling.WeatherViewModelFactory
import com.aashushaikh.weatherapplication.di.ApplicationClass
import com.bumptech.glide.Glide
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var weatherViewModel: WeatherViewModel
    @Inject
    lateinit var weatherViewModelFactory: WeatherViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        (application as ApplicationClass).applicationComponent.inject(this)

        init()

        weatherViewModel.isLoading.observe(this){
            if(it){
                binding.loader.visibility = View.VISIBLE
            }else
                binding.loader.visibility = View.GONE
        }

        binding.btnGetWeather.setOnClickListener {
            val city = binding.etCity.text.toString()
            if(city.isNotEmpty()){
                weatherViewModel.getWeatherDetail(city)
            }else{
                Toast.makeText(this, "Please enter city name", Toast.LENGTH_SHORT).show()
            }
        }
        weatherViewModel.weatherDetail.observe(this){
            val currentCondition = it.current.condition.text
            val currentTemp = it.current.temp_c.toString()
            binding.tvWeather.text = "$currentCondition, $currentTemp"
            Glide.with(this).load("https:${it.current.condition.icon}").into(binding.imgWeatherIcon)
        }
    }
    fun init(){
        weatherViewModel = ViewModelProvider(this, weatherViewModelFactory)[WeatherViewModel::class.java]
    }
}