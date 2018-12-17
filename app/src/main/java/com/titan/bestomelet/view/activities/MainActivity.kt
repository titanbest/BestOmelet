package com.titan.bestomelet.view.activities

import android.os.Bundle
import com.titan.bestomelet.R
import com.titan.bestomelet.view.base.BaseActivity
import com.titan.bestomelet.view.fragments.MainFragment

class MainActivity : BaseActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun fragment() = MainFragment()
}
