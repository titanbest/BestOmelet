package com.titan.domain.interactor

import com.titan.domain.entity.RecipeDomain
import com.titan.domain.repository.DBRoomRepository
import io.reactivex.Observable
import javax.inject.Inject

class SetRecipesToDBaseUseCase @Inject constructor(
        private val repository: DBRoomRepository
) : UseCase<Boolean, List<RecipeDomain>>() {

    override fun buildUseCaseObservable(params: List<RecipeDomain>): Observable<Boolean> =
        repository.setRecipe(params).andThen(Observable.just(true))
}