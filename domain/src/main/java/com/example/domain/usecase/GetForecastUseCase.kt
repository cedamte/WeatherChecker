package com.example.domain.usecase

import com.example.domain.model.ForecastScreenState
import com.example.domain.model.ScreenState
import io.reactivex.Observable

interface GetForecastUseCase {
    fun execute(name: String): Observable<ScreenState>
}