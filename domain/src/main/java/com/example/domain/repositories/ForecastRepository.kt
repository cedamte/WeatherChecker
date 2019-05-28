package com.example.domain.repositories

import com.example.domain.model.Forecast
import io.reactivex.Observable

interface ForecastRepository {
    fun getForecast(name: String): Observable<List<Forecast>>
}