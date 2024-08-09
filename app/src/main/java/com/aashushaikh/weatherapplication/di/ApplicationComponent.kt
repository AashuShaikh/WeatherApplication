package com.aashushaikh.weatherapplication.di

import com.aashushaikh.weatherapplication.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [Module::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

}