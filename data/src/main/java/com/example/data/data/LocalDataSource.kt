package com.example.data.data

import com.example.data.model.FavoriteCity
import io.reactivex.Maybe

interface LocalDataSource {
    fun getAllCities(): Maybe<List<FavoriteCity>>
    fun addCity(favoriteCity: FavoriteCity)
}