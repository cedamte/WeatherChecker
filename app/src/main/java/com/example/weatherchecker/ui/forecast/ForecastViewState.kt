package com.example.weatherchecker.ui.forecast

sealed class ForecastViewState {
    data class Content(val payload: List<ForecastSummary>) : ForecastViewState()
    data class Error(val message: String) : ForecastViewState()
}