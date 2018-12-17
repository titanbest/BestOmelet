package com.titan.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.titan.data.utils.DataBaseUtils
import com.titan.data.entity.RecipeEntity
import com.titan.data.tactic.RecipeDao

@Database(
        entities = [RecipeEntity::class],
        version = DataBaseUtils.VERSION,
        exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
}