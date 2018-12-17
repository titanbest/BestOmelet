package com.titan.domain.interactor

import com.titan.domain.entity.RecipeListDomain
import com.titan.domain.repository.ServerRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetRecipesFromApiUseCase @Inject constructor(
        private val repository: ServerRepository
) : UseCase<RecipeListDomain, Unit>() {

    override fun buildUseCaseObservable(params: Unit): Observable<RecipeListDomain> =
            repository.getRecipesList().toObservable()
}