package com.example.data.mapper

import com.example.data.BaseTest
import com.example.data.model.FavoriteCity
import com.example.domain.model.City
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CityMapperTest : BaseTest() {
    private lateinit var cityMapper: Mapper<FavoriteCity, City>

    @Before
    fun setup() {
        cityMapper = CityMapper()
    }

    @Test
    fun `favorite city gets mapped correctly`() {
        val favoriteCities = getFavoriteCitiesList()
        val cities = cityMapper.mapList(favoriteCities)
        val favoriteCity = favoriteCities[0]
        val city = cities[0]

        Assert.assertEquals(favoriteCity.condition, city.condition)
        Assert.assertEquals(favoriteCity.temperatureInKelvin, city.temperatureInKelvin, 0.0)
    }
}