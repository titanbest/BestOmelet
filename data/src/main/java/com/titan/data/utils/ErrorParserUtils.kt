package com.titan.data.utils

import com.titan.data.entity.ErrorsEntity
import io.reactivex.Single
import retrofit2.HttpException
import retrofit2.Retrofit

fun <T> errorParser(throwable: Throwable, retrofit: Retrofit): Single<T> {
    if (throwable is HttpException) {
        val errorConverter = retrofit.responseBodyConverter<ErrorsEntity>(
                ErrorsEntity::class.java, arrayOfNulls(0))
        return try {
            val error = errorConverter
                    .convert(throwable.response().errorBody()!!)

            var errorMessage = ""
            error.errors.forEach { list ->
                list.value.forEach {
                    errorMessage += "$it "
                }
            }
            Single.error(Throwable(errorMessage))
        } catch (e2: Exception) {
            Single.error(throwable)
        }

    }
    return Single.error(throwable)
}
