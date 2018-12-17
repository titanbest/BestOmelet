package com.titan.domain.interactor

import com.titan.domain.entity.RecipeDomain
import com.titan.domain.repository.DBRoomRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetRecipesFromDBUseCase @Inject constructor(
        private val repository: DBRoomRepository
) : UseCase<ArrayList<RecipeDomain>, Unit>() {

    override fun buildUseCaseObservable(params: Unit): Observable<ArrayList<RecipeDomain>> =
            repository.getAllRecipes().toObservable()
}