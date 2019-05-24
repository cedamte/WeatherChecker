package com.example.domain.model

data class City(val name: String,
                val isFavorite: Boolean,
                val temperatureInKelvin: Float,
                val condition: String,
                val windSpeed: String,
                val windDegrees: Float)