package com.titan.data.repository

import com.titan.data.db.AppDatabase
import com.titan.data.mapper.RecipeMapper
import com.titan.domain.entity.RecipeDomain
import com.titan.domain.repository.DBRoomRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class DBRoomStorage @Inject constructor(
        private val dbase: AppDatabase
) : DBRoomRepository {

    override fun setRecipe(recipes: List<RecipeDomain>) : Completable = Completable.fromAction {
        for (i in recipes.indices){
            dbase.recipeDao().insert(RecipeMapper.transformFromDomain(recipes[i]))
        }
    }

    override fun removeRecipe(recipe: RecipeDomain) : Completable = Completable.fromAction {
        dbase.recipeDao().remove(RecipeMapper.transformFromDomain(recipe))
    }

    override fun updateRecipe(recipe: RecipeDomain): Completable = Completable.fromAction {
        dbase.recipeDao().update(RecipeMapper.transformFromDomain(recipe))
    }

    override fun getAllRecipes(): Single<ArrayList<RecipeDomain>> = Single.fromCallable {
        val list: ArrayList<RecipeDomain> = ArrayList()
        val dataList = dbase.recipeDao().getAllRecipes()
        for (i in dataList.indices) {
            list.add(RecipeMapper.transformToDomain(dataList[i]))
        }
        return@fromCallable list
    }
}