package com.titan.bestomelet.exception

import android.content.Context
import com.titan.bestomelet.R
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException

object ErrorMessageFactory {
    private const val data = "data"

    fun create(context: Context, exception: Throwable?): String = when {
        exception is UnknownHostException -> context.getString(R.string.internet_warning)
        exception is ConnectException -> context.getString(R.string.internet_warning)
        exception is HttpException -> {
            try {
                val json = JSONObject(exception.response().errorBody()?.string())
                if (json.has(data)) {
                    json.getString(data)
                } else {
                    context.getString(R.string.server_error)
                }
            } catch (ex: Exception) {
                context.getString(R.string.server_error)
            }
        }
        exception is IOException -> context.getString(R.string.internet_warning)
        exception != null -> "${context.getString(R.string.error)} ${exception.localizedMessage}"
        else -> context.getString(R.string.internet_warning)
    }
}