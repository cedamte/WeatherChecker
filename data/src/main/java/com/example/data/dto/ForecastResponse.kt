package com.example.data.dto

data class ForecastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Prediction>,
    val message: Double
)

data class Prediction(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val rain: Rain,
    val sys: Sys,
    val weather: List<Weather>,
    val wind: Wind
)

data class Clouds(
    val all: Int
)

data class Wind(
    val deg: Double,
    val speed: Double
)

data class Sys(
    val pod: String
)

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

data class Main(
    val grnd_level: Double,
    val humidity: Int,
    val pressure: Double,
    val sea_level: Double,
    val temp: Double,
    val temp_kf: Int,
    val temp_max: Double,
    val temp_min: Double
)

class Rain(
)

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val timezone: Int
)

data class Coord(
    val lat: Double,
    val lon: Double
)