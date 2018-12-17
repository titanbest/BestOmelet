package com.titan.bestomelet.extention

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

fun <T : Any, L : LiveData<T>> LifecycleOwner.observeExt(liveData: L, body: (T?) -> Unit) = liveData.observe(this, Observer(body))