package com.example.weatherchecker.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    private val _progressObservable: MutableLiveData<Boolean> = MutableLiveData()
    protected val compositeDisposable = CompositeDisposable()

    val progressObservable: LiveData<Boolean>
        get() = _progressObservable


    open fun startProgress() {
        _progressObservable.value = true
    }

    protected fun hideProgress() {
        _progressObservable.value = false
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}