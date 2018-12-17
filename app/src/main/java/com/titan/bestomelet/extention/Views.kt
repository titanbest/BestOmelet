package com.titan.bestomelet.extention

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.GONE
}

fun CircleImageView.loadFromUrl(url: String?, requestOptions: RequestOptions? = null) {
    Glide.with(this.context.applicationContext)
            .load(url)
            .apply(requestOptions ?: RequestOptions())
            .into(this)
}