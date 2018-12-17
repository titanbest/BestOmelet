package com.titan.bestomelet.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.titan.bestomelet.utils.IDisposable

abstract class BaseViewModel : ViewModel() {
    val loading: MutableLiveData<Boolean> = MutableLiveData()

    val error: MutableLiveData<String> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        javaClass.declaredFields
                .filter { IDisposable::class.java.isAssignableFrom(it.type) }
                .forEach {
                    try {
                        it.isAccessible = true
                        (it.get(this) as IDisposable).dispose()
                    } catch (e: IllegalAccessException) {
                        Log.e(this@BaseViewModel.javaClass.name, "Can't dispose behavior", e)
                    }
                }
    }
}
