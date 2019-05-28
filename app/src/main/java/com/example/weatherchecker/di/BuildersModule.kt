package com.example.weatherchecker.di

import com.example.data.data.LocalDataSource
import com.example.data.data.LocalDataSourceImpl
import com.example.data.mapper.CityMapper
import com.example.data.mapper.FavoriteCityMapper
import com.example.data.mapper.Mapper
import com.example.data.model.FavoriteCity
import com.example.domain.model.City
import com.example.domain.usecase.AddCityUseCase
import com.example.domain.usecase.AddCityUseCaseImpl
import com.example.domain.usecase.GetCitiesUseCase
import com.example.domain.usecase.GetCitiesUseCaseImpl
import com.example.weatherchecker.HomeActivity
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

    @Binds
    abstract fun bindGetCitiesUseCase(getCitiesUseCaseImpl: GetCitiesUseCaseImpl): GetCitiesUseCase

    @Binds
    abstract fun bindAddCityUseCase(addCityUseCaseImpl: AddCityUseCaseImpl): AddCityUseCase

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun bindCityMapper(cityMapper: CityMapper): Mapper<FavoriteCity, City>

    @Binds
    abstract fun bindFavoriteCityMapper(favoriteCityMapper: FavoriteCityMapper): Mapper<City, FavoriteCity>
}