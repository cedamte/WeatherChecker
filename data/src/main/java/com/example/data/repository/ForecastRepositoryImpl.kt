package com.example.data.repository

import com.example.data.data.RemoteDataSource
import com.example.data.dto.Prediction
import com.example.data.mapper.Mapper
import com.example.domain.model.Forecast
import com.example.domain.repositories.ForecastRepository
import io.reactivex.Observable

class ForecastRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val forecastMapper: Mapper<Prediction, Forecast>) : ForecastRepository {
    override fun getForecast(name: String): Observable<List<Forecast>> = remoteDataSource.getForecast(name)
        .map { forecastMapper.mapList(it.list) }
        .toObservable()

}