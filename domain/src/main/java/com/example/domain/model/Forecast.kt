package com.example.domain.model

data class Forecast(val temperatureInKelvin: Float = Float.NEGATIVE_INFINITY,
                    val condition: String = "",
                    val windSpeed: String = "",
                    val windDegrees: Float = Float.NEGATIVE_INFINITY)