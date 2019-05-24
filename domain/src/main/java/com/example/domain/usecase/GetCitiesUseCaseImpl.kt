package com.example.domain.usecase

import com.example.domain.model.HomeScreenState
import com.example.domain.model.ScreenState
import com.example.domain.repositories.CitiesRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetCitiesUseCaseImpl @Inject constructor(private val citiesRepository: CitiesRepository) : GetCitiesUseCase {
    override fun execute(): Observable<ScreenState> {
        return citiesRepository.getAllCities()
            .map { if (it.isEmpty()) ScreenState.Empty else HomeScreenState.Content(it) }
            .onErrorReturn { ScreenState.Error(it.message ?: "Unknown Error") }
            .doOnTerminate { ScreenState.Loading(false) }
            .startWith(ScreenState.Loading(true))
    }

}