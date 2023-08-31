package com.mecdev.recipie.presentation.ui

import FindRecipeViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mecdev.recipie.R
import com.mecdev.recipie.adapters.FoundedRecipesAdapter
import com.mecdev.recipie.databinding.FragmentFindedRecipeBinding


class FindedRecipeFragment : Fragment() {

    private var _binding: FragmentFindedRecipeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FindRecipeViewModel
    private lateinit var recyclerAdapter: FoundedRecipesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFindedRecipeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[FindRecipeViewModel::class.java]
        recyclerAdapter = FoundedRecipesAdapter(arrayListOf())

        binding.foundedRecyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
        binding.foundedRecyclerView.adapter = recyclerAdapter

        viewModel.filteredRecipes.observe(viewLifecycleOwner){
            recyclerAdapter.updateList(it)
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}