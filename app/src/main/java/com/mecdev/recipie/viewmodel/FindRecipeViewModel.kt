import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mecdev.recipie.data.RecipeModel

class FindRecipeViewModel(app: Application) : AndroidViewModel(app) {
    private val db = FirebaseFirestore.getInstance()


    private val _filteredRecipes = MutableLiveData<List<RecipeModel>>()
    val filteredRecipes: LiveData<List<RecipeModel>> = _filteredRecipes
    val newList = arrayListOf<String>()
    var enteredItemsList = listOf<String>()

    private val selectedIngredients = mutableListOf<String>()

    fun addSelectedIngredient(ingredient: List<String>) {
        val formattedIngredient = ingredient.toString().lowercase()
        if (!selectedIngredients.contains(formattedIngredient)) {
            selectedIngredients.add(formattedIngredient)
        }
    }

    fun updateSelectedIngredients(ingredients: List<String>) {
        selectedIngredients.clear()
        selectedIngredients.addAll(ingredients)
    }

    fun fetchMatchingRecipes() {
        val db = Firebase.firestore

        db.collection("recipes")
            .whereEqualTo("recipe_items", enteredItemsList)
            .get()
            .addOnSuccessListener { result ->


                for (document in result) {
                    val recipe = document.toObject(RecipeModel::class.java)
                    newList.add(recipe.title)
                    println("error")
                }
            }
            .addOnFailureListener { exception ->

                println("error")
                Toast.makeText(getApplication(), exception.localizedMessage, Toast.LENGTH_SHORT).show()
            }
    }



    fun fetchMatchingRecipes2() {
        getMatchingRecipes(selectedIngredients) { matchingRecipes ->
            _filteredRecipes.value = matchingRecipes
        }
    }

    private fun getMatchingRecipes(selectedIngredients: List<String>, callback: (List<RecipeModel>) -> Unit) {
        db.collection("recipes")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val matchingRecipes = querySnapshot.documents.mapNotNull { document ->
                    val title = document.getString("title") ?: ""
                    val recipe = document.getString("recipe") ?: ""
                    val items = document.get("items") as? List<String> ?: emptyList()
                    val innerItems = document.get("inner_items") as? List<String> ?: emptyList()
                    val recipeItems = document.get("recipe_items") as? List<String> ?: emptyList()

                    if (selectedIngredients.all { ingredient -> recipeItems.any { it.equals(ingredient, ignoreCase = true) } }) {
                        RecipeModel(title, recipe, items, innerItems, recipeItems)
                    } else {
                        null
                    }
                }.toList()  // Dönüştürülmüş tarifleri List<RecipeModel> olarak döndür

                callback(matchingRecipes)
            }
            .addOnFailureListener { exception ->
                println("error")
                println(exception.localizedMessage?.toString())
            }
    }
}
