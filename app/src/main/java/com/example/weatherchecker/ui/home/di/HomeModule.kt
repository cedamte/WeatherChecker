package com.example.weatherchecker.ui.home.di

import androidx.lifecycle.ViewModelProviders
import com.example.weatherchecker.HomeActivity
import com.example.weatherchecker.ui.home.HomeViewModel
import com.example.weatherchecker.ui.home.HomeViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class HomeModule {
    @Provides
    @HomeScope
    fun provideHomeViewModel(homeViewModelFactory: HomeViewModelFactory, homeActivity: HomeActivity) =
        ViewModelProviders.of(homeActivity, homeViewModelFactory).get(HomeViewModel::class.java)
}