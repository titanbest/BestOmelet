package com.titan.bestomelet.view.fragments

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import com.titan.bestomelet.R
import com.titan.bestomelet.extention.invisible
import com.titan.bestomelet.extention.observeExt
import com.titan.bestomelet.extention.viewModel
import com.titan.bestomelet.extention.visible
import com.titan.domain.entity.RecipeDomain
import com.titan.bestomelet.interfaces.CallbackOpenSite
import com.titan.bestomelet.utils.hideKeyboardFrom
import com.titan.bestomelet.view.activities.ActivityNavigator
import com.titan.bestomelet.view.adapters.RecipeRecyclerAdapter
import com.titan.bestomelet.view.base.BaseFragment
import com.titan.bestomelet.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment(R.layout.fragment_main), CallbackOpenSite {
    private lateinit var viewModel: MainViewModel
    private val recipes: ArrayList<RecipeDomain> = ArrayList()
    private var isNetworkConnected: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initEditView()
        initRecyclerView()

        viewModel = viewModel(viewModelFactory) {
            observeExt(getDefaultRecipesList, { setRecipesToRecycler(it) })
        }
    }

    private fun initEditView() {
        searchEditDish.setOnClickListener { searchEditDish(editSearchDish.text.toString()) }

        clearEditDish.setOnClickListener { editSearchDish.text = null }

        editSearchDish.setOnEditorActionListener({ textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) searchEditDish(textView.text.toString())
            return@setOnEditorActionListener false
        })

        editSearchDish.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(text: Editable?) {
                clearEditDish.visibility = if (text!!.isNotEmpty()) {
                    View.VISIBLE
                } else {
                    observeExt(viewModel.getDefaultRecipesList, { setRecipesToRecycler(it) })
                    View.GONE
                }
            }

            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    private fun initRecyclerView() {
        listRecipes.apply { this.setHasFixedSize(true) }
        listRecipes.layoutManager = LinearLayoutManager(activity!!)
        listRecipes.adapter = RecipeRecyclerAdapter(recipes, activity!!, this@MainFragment)
    }

    private fun setRecipesToRecycler(list: List<RecipeDomain>?) = list?.let {
        recipes.clear()
        recipes.addAll(list)
        ifListEmpty(recipes.isEmpty())
        textWarning.visibility = if (recipes.isEmpty()) View.VISIBLE else View.GONE
        listRecipes.adapter!!.notifyDataSetChanged()
    }

    private fun ifListEmpty(isEmptyList: Boolean) {
        if (isEmptyList) {
            textWarning.visible()
        } else {
            textWarning.invisible()
        }
    }

    private fun searchEditDish(text: String?) = text?.let {
        if (text.isNotEmpty()) {
            hideKeyboardFrom(activity!!, editSearchDish)
            viewModel.searchRecipeList(text)
            observeExt(viewModel.getSearchList, { setRecipesToRecycler(it) })
        } else notify(getString(R.string.set_dish))
    }

    override fun openSiteWithRecipe(urlRecipe: String) {
        if (isNetworkConnected) ActivityNavigator().startBrowserFromUrl(context as AppCompatActivity, urlRecipe)
        else notify(getString(R.string.internet_warning))
    }

    override fun isNetworkConnected(isConnected: Boolean?) {
        isConnected?.let {
            isNetworkConnected = isConnected
            system_edit.visibility = if (isNetworkConnected) View.VISIBLE else View.GONE
        }
    }
}