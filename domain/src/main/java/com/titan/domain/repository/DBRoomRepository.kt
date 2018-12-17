package com.titan.domain.repository

import com.titan.domain.entity.RecipeDomain
import io.reactivex.Completable
import io.reactivex.Single

interface DBRoomRepository {

    fun setRecipe(recipes: List<RecipeDomain>): Completable

    fun removeRecipe(recipe: RecipeDomain): Completable

    fun updateRecipe(recipe: RecipeDomain): Completable

    fun getAllRecipes(): Single<ArrayList<RecipeDomain>>
}