package com.example.weatherchecker.ui.forecast

data class ForecastSummary(
    val temperature: String,
    val windSpeed: String,
    val windDegrees: Double,
    val condition: String,
    val date: String
)
