package com.titan.bestomelet.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.titan.domain.entity.RecipeDomain
import com.titan.domain.entity.RecipeListDomain
import com.titan.domain.interactor.GetRecipesFromDBUseCase
import com.titan.domain.interactor.SearchRecipeListFromApiUseCase
import com.titan.bestomelet.extention.Flow
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val getRecipesListFromDBFlow: Flow<Unit, ArrayList<RecipeDomain>, GetRecipesFromDBUseCase>,
        private val searchRecipeListFlow: Flow<String, RecipeListDomain, SearchRecipeListFromApiUseCase>
) : BaseViewModel() {

    val getDefaultRecipesList: MutableLiveData<List<RecipeDomain>> = object : MutableLiveData<List<RecipeDomain>>() {
        override fun onActive() {
            super.onActive()
            getRecipesListFromDBFlow.run(Unit) {
                value = it
            }
        }
    }

    val getSearchList: MutableLiveData<List<RecipeDomain>> = object : MutableLiveData<List<RecipeDomain>>() {}

    fun searchRecipeList(dish: String) {
        if (dish.isNotEmpty()){
            searchRecipeListFlow.run(dish) {
                getSearchList.value = it.results
            }
        }
    }
}