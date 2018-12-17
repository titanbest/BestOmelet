package com.titan.bestomelet.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.titan.domain.entity.RecipeDomain
import com.titan.domain.interactor.GetRecipesFromApiUseCase
import com.titan.bestomelet.extention.Flow
import com.titan.domain.entity.RecipeListDomain
import com.titan.domain.interactor.SetRecipesToDBaseUseCase
import javax.inject.Inject

class SplashViewModel @Inject constructor(
        private val getRecipesListFlow: Flow<Unit, RecipeListDomain, GetRecipesFromApiUseCase>,
        private val setRecipesToDBFlow: Flow<List<RecipeDomain>, Boolean, SetRecipesToDBaseUseCase>
) : BaseViewModel() {

    val getResultFromLoadList: MutableLiveData<Boolean> = object : MutableLiveData<Boolean>() {
        override fun onActive() {
            super.onActive()
            getRecipesListFlow.run(Unit) {
                setRecipesToDBFlow.run(it.results) {
                    value = it
                }
            }
        }
    }
}