package com.titan.data.tactic

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.titan.data.entity.RecipeEntity

@Dao
interface RecipeDao : BaseDao<RecipeEntity>{

    @Query("SELECT * FROM RecipeEntity WHERE title = :title")
    fun getRecipe(title: String): RecipeEntity

    @Query("SELECT * FROM RecipeEntity")
    fun getAllRecipes(): List<RecipeEntity>
}