package com.example.weatherchecker.ui.forecast

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.model.ScreenState
import com.example.domain.usecase.GetForecastUseCase
import com.example.weatherchecker.base.BaseViewModel
import com.example.weatherchecker.base.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ForecastViewModel(private val getForecastUseCase: GetForecastUseCase) : BaseViewModel() {
    private val _forecastScreenStateObservable = MutableLiveData<ScreenState>()

    val forecastScreenStateObservable: LiveData<ScreenState>
        get() = _forecastScreenStateObservable

    fun getForecast(name: String?) {
        name?.let {
            getForecastUseCase.execute(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { _forecastScreenStateObservable.value = it }
                .addTo(compositeDisposable)
        }
    }
}