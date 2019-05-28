package com.example.data.data

import com.example.data.model.FavoriteCity
import io.reactivex.Flowable

interface LocalDataSource {
    fun getAllCities(): Flowable<List<FavoriteCity>>
    fun addCity(favoriteCity: FavoriteCity)
}