package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.BuildConfig
import com.example.data.model.FavoriteCity

@Database(entities = [FavoriteCity::class], version = BuildConfig.DATABASE_VERSION)
abstract class WeatherCheckerDatabase : RoomDatabase() {
    abstract fun citiesDAO(): CitiesDAO
}