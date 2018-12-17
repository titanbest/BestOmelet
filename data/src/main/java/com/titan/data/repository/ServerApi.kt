package com.titan.data.repository

import com.titan.domain.entity.RecipeListDomain
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "http://www.recipepuppy.com/api/"

interface ServerApi {

    @GET("?i=onions,garlic&q=omelet&p=3")
    fun loadDefaultRecipesList(): Single<RecipeListDomain>

    @GET("/api/")
    fun searchRecipe(@Query(value = "q") dish: String): Single<RecipeListDomain>
}