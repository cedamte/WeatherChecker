package com.example.domain.model

data class Forecast(val temperatureInKelvin: Double = Double.NEGATIVE_INFINITY,
                    val condition: String = "",
                    val windSpeed: Double = Double.NEGATIVE_INFINITY,
                    val windDegrees: Double = Double.NEGATIVE_INFINITY)