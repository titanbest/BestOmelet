package com.titan.data.mapper

import com.titan.domain.mapper.InputMapper
import com.titan.domain.mapper.OutputMapper
import com.titan.data.entity.RecipeEntity
import com.titan.domain.entity.RecipeDomain

object RecipeMapper : OutputMapper<RecipeDomain, RecipeEntity>, InputMapper<RecipeEntity, RecipeDomain> {
    override fun transformFromDomain(item: RecipeDomain): RecipeEntity =
            RecipeEntity(item.title, item.href, item.ingredients, item.thumbnail)

    override fun transformToDomain(item: RecipeEntity): RecipeDomain =
            RecipeDomain(item.title, item.href, item.ingredients, item.thumbnail)
}