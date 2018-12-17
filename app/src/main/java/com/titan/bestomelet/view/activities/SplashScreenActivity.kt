package com.titan.bestomelet.view.activities

import android.os.Bundle
import com.titan.bestomelet.R
import com.titan.bestomelet.extention.observeExt
import com.titan.bestomelet.extention.viewModel
import com.titan.bestomelet.view.base.BaseActivity
import com.titan.bestomelet.view.base.BaseFragment
import com.titan.bestomelet.viewmodel.SplashViewModel

class SplashScreenActivity : BaseActivity(R.layout.activity_splash) {
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModel(viewModelFactory) {
            observeExt(getResultFromLoadList, { startMainActivity(it) })
        }
    }

    private fun startMainActivity(isLoaded: Boolean?) = isLoaded?.let {
        if (it) ActivityNavigator().startMainActivity(this)
    }

    override fun isNetworkConnected(isConnected: Boolean?) {
        super.isNetworkConnected(isConnected)
        isConnected?.let {
            if (!isConnected) startMainActivity(true)
        } ?: startMainActivity(true)
    }

    override fun fragment(): BaseFragment? = null
}