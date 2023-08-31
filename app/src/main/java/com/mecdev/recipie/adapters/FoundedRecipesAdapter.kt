package com.mecdev.recipie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mecdev.recipie.R
import com.mecdev.recipie.data.RecipeModel
import com.mecdev.recipie.databinding.FoundedRecipeItemRowBinding

class FoundedRecipesAdapter (private val list: ArrayList<RecipeModel>): RecyclerView.Adapter<FoundedRecipesAdapter.ViewHolder>(){

    class ViewHolder(val binding: FoundedRecipeItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoundedRecipesAdapter.ViewHolder {
        val binding=FoundedRecipeItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.imageView.setImageResource(R.drawable.ic_launcher_background)
        holder.binding.titleText.text = list[position].title
    }




    fun updateList(l: List<RecipeModel>){
        list.clear()
        list.addAll(l)
        notifyDataSetChanged()
    }
}