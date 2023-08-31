package com.mecdev.recipie.data


import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class RecipeRepo {
    private val db = Firebase.firestore

    suspend fun getMatchingRecipes(matchingUUIDs: List<String>): List<RecipeModel> {
        val recipes = mutableListOf<RecipeModel>()

        for (uuid in matchingUUIDs) {
            val document = db.collection("recipes").document(uuid).get().await()
            if (document != null) {
                val title = document.getString("title") ?: ""
               // val recipe = RecipeModel(uuid, title)
                //recipes.add(recipe)
            }
        }

        return recipes
    }
}