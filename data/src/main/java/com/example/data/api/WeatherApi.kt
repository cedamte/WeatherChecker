package com.example.data.api

import com.example.data.BuildConfig
import com.example.data.dto.ForecastResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(BuildConfig.FORECAST_ENDPOINT)
    fun getForecast(@Query("q")name: String): Single<ForecastResponse>
}