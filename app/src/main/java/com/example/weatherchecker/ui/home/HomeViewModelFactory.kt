package com.example.weatherchecker.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.AddCityUseCase
import com.example.domain.usecase.GetCitiesUseCase
import java.lang.IllegalArgumentException
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(private val getCitiesUseCase: GetCitiesUseCase,
                                               private val addCityUseCase: AddCityUseCase) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(addCityUseCase, getCitiesUseCase) as T
        }
        throw IllegalArgumentException("The class should be an instance: " + HomeViewModel::class.java.simpleName)
    }

}