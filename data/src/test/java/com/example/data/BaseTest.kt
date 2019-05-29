package com.example.data

import com.example.data.model.FavoriteCity

open class BaseTest() {
    fun getFavoriteCitiesList() =
            listOf(FavoriteCity(0, "London", true, 20.0, "Sunny",
                23.0, 23.0))


}