package com.titan.domain.entity

class RecipeListDomain(
    val title: String,
    val version: Float,
    val href: String,
    val results: List<RecipeDomain>
)