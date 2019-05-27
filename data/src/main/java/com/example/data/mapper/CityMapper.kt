package com.example.data.mapper

import com.example.data.model.FavoriteCity
import com.example.domain.model.City
import javax.inject.Inject

class CityMapper @Inject constructor() : Mapper<FavoriteCity, City> {
    override fun map(item: FavoriteCity): City {
        return with(item) {
            City(name = name,
                isFavorite = isFavorite,
                temperatureInKelvin = temperatureInKelvin,
                condition = condition,
                windSpeed = windSpeed,
                windDegrees = windDegrees)
        }
    }

}