package com.example.weatherchecker.ui.forecast.di

import com.example.weatherchecker.ui.forecast.ForecastActivity
import com.example.weatherchecker.ui.home.di.HomeScope
import dagger.Binds
import dagger.Module

@Module
abstract class ForecastActivityModule {
    @Binds
    @HomeScope
    abstract fun forecastActivity(forecastActivity: ForecastActivity): ForecastActivity
}