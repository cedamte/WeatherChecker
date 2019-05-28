package com.example.weatherchecker.di

import com.example.data.data.LocalDataSource
import com.example.data.data.LocalDataSourceImpl
import com.example.data.data.RemoteDataSource
import com.example.data.data.RemoteDataSourceImpl
import com.example.data.dto.Prediction
import com.example.data.mapper.CityMapper
import com.example.data.mapper.FavoriteCityMapper
import com.example.data.mapper.ForecastMapper
import com.example.data.mapper.Mapper
import com.example.data.model.FavoriteCity
import com.example.domain.model.City
import com.example.domain.model.Forecast
import com.example.domain.usecase.*
import com.example.weatherchecker.HomeActivity
import com.example.weatherchecker.ui.forecast.ForecastActivity
import com.example.weatherchecker.ui.forecast.di.ForecastModule
import com.example.weatherchecker.ui.forecast.di.ForecastScope
import com.example.weatherchecker.ui.home.di.HomeModule
import com.example.weatherchecker.ui.home.di.HomeScope
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [HomeModule::class])
    @HomeScope
    abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [ForecastModule::class])
    @ForecastScope
    abstract fun forecastActivity(): ForecastActivity

    @Binds
    abstract fun bindGetCitiesUseCase(getCitiesUseCaseImpl: GetCitiesUseCaseImpl): GetCitiesUseCase

    @Binds
    abstract fun bindAddCityUseCase(addCityUseCaseImpl: AddCityUseCaseImpl): AddCityUseCase

    @Binds
    abstract fun bindGetForecastUseCase(getForecastUseCaseImpl: GetForecastUseCaseImpl): GetForecastUseCase

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    abstract fun bindCityMapper(cityMapper: CityMapper): Mapper<FavoriteCity, City>

    @Binds
    abstract fun bindFavoriteCityMapper(favoriteCityMapper: FavoriteCityMapper): Mapper<City, FavoriteCity>

    @Binds
    abstract fun bindForecastMapper(forecastMapper: ForecastMapper): Mapper<Prediction, Forecast>
}