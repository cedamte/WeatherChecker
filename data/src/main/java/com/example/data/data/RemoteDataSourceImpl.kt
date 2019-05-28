package com.example.data.data

import com.example.data.api.WeatherApi
import com.example.data.dto.ForecastResponse
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val weatherApi: WeatherApi) : RemoteDataSource {
    override fun getForecast(name: String) = weatherApi.getForecast(name)

}