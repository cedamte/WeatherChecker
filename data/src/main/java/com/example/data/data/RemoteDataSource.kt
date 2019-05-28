package com.example.data.data

import com.example.data.dto.ForecastResponse
import io.reactivex.Single

interface RemoteDataSource {
    fun getForecast(name: String): Single<ForecastResponse>
}