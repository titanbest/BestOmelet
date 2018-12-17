package com.titan.domain.interactor

import com.titan.domain.entity.RecipeListDomain
import com.titan.domain.repository.ServerRepository
import io.reactivex.Observable
import javax.inject.Inject

class SearchRecipeListFromApiUseCase @Inject constructor(
        private val serverRepo: ServerRepository
) : UseCase<RecipeListDomain, String>() {

    override fun buildUseCaseObservable(params: String): Observable<RecipeListDomain> =
            serverRepo.searchRecipe(params).toObservable()
}