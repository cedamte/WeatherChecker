package com.example.domain.usecase

import com.example.domain.model.City
import com.example.domain.model.ScreenState
import com.example.domain.repositories.CitiesRepository
import io.reactivex.Observable
import javax.inject.Inject

class AddCityUseCaseImpl @Inject constructor(private val citiesRepository: CitiesRepository) : AddCityUseCase {
    override fun execute(name: String): Observable<ScreenState> {
        return Observable.fromCallable {
            citiesRepository.addCity(City(name = name, isFavorite = true))
            ScreenState.Loading(false) as ScreenState }
            .doOnSubscribe { ScreenState.Loading(true) }
    }
}