package com.example.data.data

import com.example.data.db.WeatherCheckerDatabase
import com.example.data.model.FavoriteCity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val weatherCheckerDatabase: WeatherCheckerDatabase) :
    LocalDataSource {

    override fun addCity(favoriteCity: FavoriteCity) {
        weatherCheckerDatabase.citiesDAO().insertCity(favoriteCity)
    }

    override fun getAllCities() = weatherCheckerDatabase.citiesDAO().getAllCities()

}