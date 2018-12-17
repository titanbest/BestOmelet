package com.titan.domain.repository

import com.titan.domain.entity.RecipeListDomain
import io.reactivex.Single

interface ServerRepository {

    fun getRecipesList(): Single<RecipeListDomain>

    fun searchRecipe(dish: String): Single<RecipeListDomain>
}