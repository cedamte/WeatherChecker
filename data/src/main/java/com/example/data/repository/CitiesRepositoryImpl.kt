package com.example.data.repository

import com.example.data.data.LocalDataSource
import com.example.data.mapper.Mapper
import com.example.data.model.FavoriteCity
import com.example.domain.model.City
import com.example.domain.repositories.CitiesRepository
import io.reactivex.Observable

class CitiesRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val cityMapper: Mapper<FavoriteCity, City>,
    private val favoriteCityMapper: Mapper<City, FavoriteCity>
) : CitiesRepository {
    override fun addCity(city: City) {
        localDataSource.addCity(favoriteCityMapper.map(city))
    }

    override fun getAllCities(): Observable<List<City>> {
        return localDataSource.getAllCities()
            .map { cityMapper.mapList(it) }
            .toObservable()
    }

}