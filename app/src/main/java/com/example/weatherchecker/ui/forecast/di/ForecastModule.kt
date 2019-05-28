package com.example.weatherchecker.ui.forecast.di

import androidx.lifecycle.ViewModelProviders
import com.example.weatherchecker.ui.forecast.ForecastActivity
import com.example.weatherchecker.ui.forecast.ForecastViewModel
import com.example.weatherchecker.ui.forecast.ForecastViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ForecastModule {
    @Provides
    @ForecastScope
    fun provideForecastViewModel(forecastViewModelFactory: ForecastViewModelFactory, forecastActivity: ForecastActivity)
        = ViewModelProviders.of(forecastActivity, forecastViewModelFactory).get(ForecastViewModel::class.java)
}