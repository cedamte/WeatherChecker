package com.example.weatherchecker

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.domain.model.HomeScreenState
import com.example.domain.model.ScreenState
import com.example.domain.usecase.AddCityUseCase
import com.example.domain.usecase.GetCitiesUseCase
import com.example.weatherchecker.ui.home.HomeViewModel
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest : BaseTest() {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val addCityUseCase: AddCityUseCase = mock()
    private val getCitiesUseCase: GetCitiesUseCase = mock()

    private val observer: Observer<ScreenState> = mock()

    private lateinit var homeViewModel: HomeViewModel

    @Before
    override fun setup() {
        super.setup()
        homeViewModel = HomeViewModel(addCityUseCase, getCitiesUseCase)
    }

    @Test
    fun `data is successfully retrieved`() {
        val contentState = HomeScreenState.Content(emptyList())
        given(getCitiesUseCase.execute()).willReturn(Observable.just(contentState))

        homeViewModel.homeScreenStateObservable.observeForever(observer)

        homeViewModel.getSavedCities()

        verify(observer).onChanged(contentState)
    }

    @Test
    fun `error message is propagated successfully`() {
        val errorScreenState = ScreenState.Error("Connection timeout")
        given(getCitiesUseCase.execute()).willReturn(Observable.just(errorScreenState))

        homeViewModel.homeScreenStateObservable.observeForever(observer)
        homeViewModel.getSavedCities()

        verify(observer).onChanged(errorScreenState)
    }
}