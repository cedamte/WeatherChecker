package com.example.data.mapper

import com.example.data.model.FavoriteCity
import com.example.domain.model.City
import javax.inject.Inject

class FavoriteCityMapper @Inject constructor() : Mapper<City, FavoriteCity> {
    override fun map(item: City): FavoriteCity {
        return with(item) {
            FavoriteCity(name = name,
                isFavorite = isFavorite,
                temperatureInKelvin = temperatureInKelvin,
                condition = condition,
                windSpeed = windSpeed,
                windDegrees = windDegrees)
        }
    }

}