package com.titan.bestomelet.view.activities

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity

class ActivityNavigator {
    fun startMainActivity(context: AppCompatActivity) {
        context.startActivity(Intent(context, MainActivity::class.java))
        context.finish()
    }

    fun startBrowserFromUrl(context: AppCompatActivity, url: String){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(browserIntent)
    }
}
