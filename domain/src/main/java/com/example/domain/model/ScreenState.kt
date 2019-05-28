package com.example.domain.model

sealed class ScreenState {
    data class Loading(val isLoading: Boolean) : ScreenState()
    data class Error(val errorMessage: String) : ScreenState()
    object Empty : ScreenState()
}

sealed class HomeScreenState : ScreenState() {
    data class Content(val payload: List<City>): HomeScreenState()
    object LaunchCitySelection : HomeScreenState()
    object ShowFavoriteOption : HomeScreenState()
}

sealed class ForecastScreenState : ScreenState() {
    data class Content(val payload: List<Forecast>) : ForecastScreenState()
}