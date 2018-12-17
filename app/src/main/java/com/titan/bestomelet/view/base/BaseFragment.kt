package com.titan.bestomelet.view.base

import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.titan.bestomelet.extention.observeExt
import com.titan.bestomelet.utils.ConnectionLiveData
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment(private val layoutId: Int) : DaggerFragment() {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private var binding: ViewDataBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding?.root ?: inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeExt(ConnectionLiveData(activity!!), { isNetworkConnected(it) })
    }

    open fun onBackPressed(): Boolean = true

    open fun isNetworkConnected(isConnected: Boolean?) {}

    internal fun notify(message: String?) = message?.let { Toast.makeText(activity, message, Toast.LENGTH_SHORT).show() }
}