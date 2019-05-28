package com.example.weatherchecker.di

import android.app.Application
import androidx.room.Room
import com.example.data.db.WeatherCheckerDatabase
import com.example.weatherchecker.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideWeatherCheckerDatabase(application: Application): WeatherCheckerDatabase {
        return Room.databaseBuilder(application, WeatherCheckerDatabase::class.java, BuildConfig.DATABASE_NAME)
            .build()
    }
}