package com.titan.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class RecipeEntity(
        @PrimaryKey
        val title: String,
        val href: String,
        val ingredients: String,
        val thumbnail: String
)