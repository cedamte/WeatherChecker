package com.example.domain.usecase

import com.example.domain.model.ForecastScreenState
import com.example.domain.model.ScreenState
import com.example.domain.repositories.ForecastRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetForecastUseCaseImpl @Inject constructor(private val forecastRepository: ForecastRepository) :
    GetForecastUseCase {
    override fun execute(name: String): Observable<ScreenState> {
        return forecastRepository.getForecast(name)
            .map { if (it.isEmpty()) ScreenState.Empty else ForecastScreenState.Content(it) }
            .onErrorReturn { ScreenState.Error(it.message ?: "Unknown Error") }
            .doOnTerminate { ScreenState.Loading(false) }
            .startWith { ScreenState.Loading(true) }
    }

}