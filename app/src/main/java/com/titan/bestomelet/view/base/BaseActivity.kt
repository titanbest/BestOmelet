package com.titan.bestomelet.view.base

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import com.titan.bestomelet.R
import com.titan.bestomelet.extention.inTransaction
import com.titan.bestomelet.extention.observeExt
import com.titan.bestomelet.utils.ConnectionLiveData
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity(private val layoutId: Int) : DaggerAppCompatActivity() {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        addFragment(savedInstanceState)
        observeExt(ConnectionLiveData(this), ::isNetworkConnected)
    }

    override fun onBackPressed() {
        fragment()?.let {
            if ((supportFragmentManager.findFragmentById(R.id.fragmentContainer) as BaseFragment).onBackPressed())
                super.onBackPressed()
        }
    }

    private fun addFragment(savedInstanceState: Bundle?) =
            savedInstanceState ?: fragment()?.let {
                supportFragmentManager.inTransaction {
                    add(R.id.fragmentContainer, it)
                }
            }

    abstract fun fragment(): BaseFragment?

    open fun isNetworkConnected(isConnected: Boolean?) {}
}
