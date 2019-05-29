package com.example.weatherchecker.ui.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.model.ForecastScreenState
import com.example.domain.model.ScreenState
import com.example.domain.usecase.GetForecastUseCase
import com.example.weatherchecker.base.BaseViewModel
import com.example.weatherchecker.base.addTo
import com.example.weatherchecker.base.toForecastSummary
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ForecastViewModel(private val getForecastUseCase: GetForecastUseCase) : BaseViewModel() {
    private val _forecastViewStateObservable = MutableLiveData<ForecastViewState>()

    val forecastViewStateObservable: LiveData<ForecastViewState>
        get() = _forecastViewStateObservable

    fun getForecast(name: String?) {
        name?.let {
            getForecastUseCase.execute(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { processScreenState(it) }
                .addTo(compositeDisposable)
        }
    }

    private fun processScreenState(screenState: ScreenState) {
        when (screenState) {
            is ForecastScreenState.Content -> {
                val forecastSummaryList = screenState.payload.map { it.toForecastSummary() }
                _forecastViewStateObservable.value = ForecastViewState.Content(forecastSummaryList)
                hideProgress()
            }
            is ScreenState.Error ->
                _forecastViewStateObservable.value = ForecastViewState.Error(screenState.errorMessage)
            is ScreenState.Loading -> if(screenState.isLoading) startProgress() else hideProgress()
        }
    }
}