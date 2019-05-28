package com.example.weatherchecker.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.model.HomeScreenState
import com.example.domain.model.ScreenState
import com.example.domain.usecase.AddCityUseCase
import com.example.domain.usecase.GetCitiesUseCase
import com.example.weatherchecker.base.BaseViewModel
import com.example.weatherchecker.base.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel (private val addCityUseCase: AddCityUseCase,
                     private val getCitiesUseCase: GetCitiesUseCase) : BaseViewModel() {
    private val _homeScreenStateObservable = MutableLiveData<ScreenState>()

    val homeScreenStateObservable: LiveData<ScreenState>
        get() = _homeScreenStateObservable

    fun getSavedCities() {
        getCitiesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { hideProgress() }
            .subscribe { _homeScreenStateObservable.value = it }
            .addTo(compositeDisposable)
    }

    fun onAddCitySelected() {
        _homeScreenStateObservable.value = HomeScreenState.LaunchCitySelection
    }

    fun onCitySelected(name: String) {
        addCityUseCase.execute(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { hideProgress() }
            .doOnComplete { _homeScreenStateObservable.value = HomeScreenState.ShowFavoriteOption }
            .subscribe()
            .addTo(compositeDisposable)
    }

    fun onSaveCitySelected() {

    }

    fun onIgnoreCityAsFavoriteSelected() {

    }
}