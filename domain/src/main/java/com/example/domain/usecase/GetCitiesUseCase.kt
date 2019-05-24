package com.example.domain.usecase

import com.example.domain.model.ScreenState
import io.reactivex.Observable

interface GetCitiesUseCase {
    fun execute(): Observable<ScreenState>
}