package com.titan.data.repository

import com.titan.domain.entity.RecipeListDomain
import com.titan.domain.repository.ServerRepository
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class ServerStorage @Inject constructor(
        private val retrofit: Retrofit,
        private val serverApi: ServerApi
) : ServerRepository {

    override fun getRecipesList(): Single<RecipeListDomain> =
            Single.fromCallable {
                serverApi.loadDefaultRecipesList().blockingGet()
            }.onErrorResumeNext {
                errorParser(it)
            }

    override fun searchRecipe(dish: String): Single<RecipeListDomain> = Single.just(dish)
            .flatMap {
                serverApi.searchRecipe(dish)
                        .onErrorResumeNext { errorParser(it) }
                        .map {
                            return@map it
                        }
            }

    private fun <T> errorParser(throwable: Throwable): Single<T> {
        return com.titan.data.utils.errorParser(throwable, retrofit)
    }
}