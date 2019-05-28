package com.example.data.mapper

import com.example.data.dto.Prediction
import com.example.domain.model.Forecast
import javax.inject.Inject

class ForecastMapper @Inject constructor() : Mapper<Prediction, Forecast> {
    override fun map(item: Prediction): Forecast {
        return with(item) {
            Forecast(temperatureInKelvin = main.temp,
                condition = weather[0].description,
                time = dt,
                windSpeed = wind.speed,
                windDegrees = wind.deg)
        }
    }

}