package com.example.weatherchecker.ui.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.GetForecastUseCase
import java.lang.IllegalArgumentException
import javax.inject.Inject

class ForecastViewModelFactory @Inject constructor(private val getForecastUseCase: GetForecastUseCase)
    : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ForecastViewModel::class.java)) {
            return ForecastViewModel(getForecastUseCase) as T
        }
        throw IllegalArgumentException("The class should be an instance: " + ForecastViewModel::class.java.simpleName)
    }

}