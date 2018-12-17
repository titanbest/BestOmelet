package com.titan.bestomelet.view.adapters

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.titan.bestomelet.R
import com.titan.domain.entity.RecipeDomain
import com.titan.bestomelet.extention.loadFromUrl
import com.titan.bestomelet.interfaces.CallbackOpenSite
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipeRecyclerAdapter(
        private val items: ArrayList<RecipeDomain>,
        private val context: Context,
        private val callbackOpenSite: CallbackOpenSite
) : RecyclerView.Adapter<RecipeRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false))

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mItem = items[position]
        holder.itemImage.loadFromUrl(mItem.thumbnail)
        holder.itemTitle.text = mItem.title
        holder.itemDescription.text = mItem.ingredients
        holder.itemFull.setOnClickListener {
            callbackOpenSite.openSiteWithRecipe(mItem.href)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImage: CircleImageView = view.imageRecipe
        val itemTitle: AppCompatTextView = view.textRecipeTitle
        val itemDescription: AppCompatTextView = view.textRecipeDescription
        val itemFull: ConstraintLayout = view.itemRecipe
    }
}