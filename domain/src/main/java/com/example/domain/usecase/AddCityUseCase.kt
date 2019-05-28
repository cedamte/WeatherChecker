package com.example.domain.usecase

import com.example.domain.model.ScreenState
import io.reactivex.Observable

interface AddCityUseCase {
    fun execute(name: String): Observable<ScreenState>
}