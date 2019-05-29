package com.example.domain

import com.example.domain.model.City
import com.example.domain.model.HomeScreenState
import com.example.domain.model.ScreenState
import com.example.domain.repositories.CitiesRepository
import com.example.domain.usecase.GetCitiesUseCase
import com.example.domain.usecase.GetCitiesUseCaseImpl
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.SocketException

@RunWith(MockitoJUnitRunner::class)
class GetCitiesUseCaseTest {
    private val citiesRepository: CitiesRepository = mock()

    private lateinit var getCitiesUseCase: GetCitiesUseCase

    @Before
    fun setup() {
        getCitiesUseCase = GetCitiesUseCaseImpl(citiesRepository)
    }

    @Test
    fun `attempts to retrieve all cities`() {
        given(citiesRepository.getAllCities()).willReturn(mock())

        getCitiesUseCase.execute().test()

        verify(citiesRepository).getAllCities()
    }

    @Test
    fun `loading event is dispatched at the start`() {
        given(citiesRepository.getAllCities()).willReturn(mock())

        getCitiesUseCase.execute().test().assertValueAt(0) { it == ScreenState.Loading(true) }
    }

    @Test
    fun `content event is dispatched when cities are retrieved successfully`() {
        val result = listOf(City("London", true))
        given(citiesRepository.getAllCities()).willReturn(Observable.just(result))

        getCitiesUseCase.execute().test().assertValueAt(1) { (it as HomeScreenState.Content).payload == result }
    }

    @Test
    fun `error event is dispatched when operation fails`() {
        val message = "Operation Timed Out"
        given(citiesRepository.getAllCities()).willReturn(Observable.error(SocketException(message)))

        getCitiesUseCase.execute().test().assertValueAt(1) { (it as ScreenState.Error).errorMessage == message }
    }
}