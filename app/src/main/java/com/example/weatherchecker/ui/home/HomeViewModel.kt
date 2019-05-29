package com.example.weatherchecker.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.model.City
import com.example.domain.model.HomeScreenState
import com.example.domain.model.ScreenState
import com.example.domain.usecase.AddCityUseCase
import com.example.domain.usecase.GetCitiesUseCase
import com.example.weatherchecker.base.BaseViewModel
import com.example.weatherchecker.base.addTo
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.model.AddressComponent
import com.google.android.libraries.places.api.model.Place
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    private val addCityUseCase: AddCityUseCase,
    private val getCitiesUseCase: GetCitiesUseCase) : BaseViewModel() {
    private val _homeScreenStateObservable = MutableLiveData<ScreenState>()
    private lateinit var place: Place

    val homeScreenStateObservable: LiveData<ScreenState>
        get() = _homeScreenStateObservable

    fun start() {
        if ((_homeScreenStateObservable.value is HomeScreenState.Content).not()) {
            _homeScreenStateObservable.value = null
        }
    }

    fun getSavedCities() {
        getCitiesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { hideProgress() }
            .subscribe { _homeScreenStateObservable.value = it }
            .addTo(compositeDisposable)
    }

    fun onSearchSelected() {
        _homeScreenStateObservable.value = HomeScreenState.LaunchCitySelection
    }

    fun onCitySelected(city: City) {
        _homeScreenStateObservable.value = HomeScreenState.LaunchForecastActivity(city.name)
    }

    fun onFavoriteOptionSelected(shouldFavorite: Boolean) {
        if (shouldFavorite && ::place.isInitialized) {
            addCityUseCase.execute(extractCityAndCountry(place))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { _homeScreenStateObservable.value = it }
                .addTo(compositeDisposable)
        } else if (::place.isInitialized) {
            _homeScreenStateObservable.value = HomeScreenState.LaunchForecastActivity(extractCityAndCountry(place))
        }
    }

    fun onSearchResult(place: Place) {
        this.place = place
        _homeScreenStateObservable.value = HomeScreenState.ShowFavoriteOption
    }

    fun onSearchError(status: Status) {
        _homeScreenStateObservable.value = ScreenState.Error(status.statusMessage ?: "Unknown Error")
    }

    private fun extractCityAndCountry(place: Place): String {
        val addressComponents = place.addressComponents?.asList()
        val country = addressComponents?.firstOrNull<AddressComponent?> {
            it?.types?.firstOrNull<String?>{ result -> result == "country"} != null
        }
        return "${place.name},${country?.shortName}"
    }
}