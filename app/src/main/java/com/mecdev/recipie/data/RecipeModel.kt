package com.mecdev.recipie.data

data class RecipeModel(

    val title: String,
    val recipe: String,
    val items: List<String>,
    val inner_items: List<String>,
    val recipe_items: List<String>,

)
