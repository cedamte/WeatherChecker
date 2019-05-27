package com.example.domain.repositories

import com.example.domain.model.City
import io.reactivex.Observable

interface CitiesRepository {
    fun getAllCities(): Observable<List<City>>
    fun addCity(city: City)
}