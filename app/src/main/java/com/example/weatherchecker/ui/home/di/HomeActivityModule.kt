package com.example.weatherchecker.ui.home.di

import com.example.weatherchecker.HomeActivity
import dagger.Binds
import dagger.Module

@Module
abstract class HomeActivityModule {
    @Binds
    @HomeScope
    abstract fun homeActivity(homeActivity: HomeActivity): HomeActivity
}