package com.example.weatherchecker.di

import com.example.data.data.LocalDataSource
import com.example.data.data.RemoteDataSource
import com.example.data.dto.Prediction
import com.example.data.mapper.Mapper
import com.example.data.model.FavoriteCity
import com.example.data.repository.CitiesRepositoryImpl
import com.example.data.repository.ForecastRepositoryImpl
import com.example.domain.model.City
import com.example.domain.model.Forecast
import com.example.domain.repositories.CitiesRepository
import com.example.domain.repositories.ForecastRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCitiesRepository(localDataSource: LocalDataSource,
        cityMapper: Mapper<FavoriteCity, City>,
        favoriteCityMapper: Mapper<City, FavoriteCity>): CitiesRepository {
        return CitiesRepositoryImpl(localDataSource, cityMapper, favoriteCityMapper)
    }

    @Provides
    @Singleton
    fun provideForecastRepository(remoteDataSource: RemoteDataSource,
                                  forecastMapper: Mapper<Prediction, Forecast>): ForecastRepository {
        return ForecastRepositoryImpl(remoteDataSource, forecastMapper)
    }
}