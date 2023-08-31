package com.mecdev.recipie.presentation.ui

import FindRecipeViewModel
import android.app.Application
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.forEach
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.mecdev.recipie.R
import com.mecdev.recipie.databinding.FragmentFindRecipeBinding

class FindRecipeFragment : Fragment() {

    private var _binding: FragmentFindRecipeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FindRecipeViewModel
    private val selectedVegatables = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFindRecipeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[FindRecipeViewModel::class.java]

        addedChip()

        binding.findRecipe.setOnClickListener {
            val newList = listOf("süt","yağ","maya","yumurta","biber","domates","peynir","zeytin","sosis")
            selectedVegatables.clear()
            viewModel.addSelectedIngredient(newList)
            viewModel.enteredItemsList = newList
            viewModel.fetchMatchingRecipes()
            viewModel.fetchMatchingRecipes2()

            registerFilterChanged()
            replaceFragment(FindedRecipeFragment())
        }
    }

    private fun registerFilterChanged() {

        binding.vegatables.forEach { child ->
            (child as? Chip)?.setOnCheckedChangeListener { _, _ ->
                registerFilterChanged()
            }
        }
        val ids = binding.vegatables.checkedChipIds
        val titles = mutableListOf<CharSequence>()

        ids.forEach { id ->
            titles.add(binding.vegatables.findViewById<Chip>(id).text)
        }

        val text = titles.joinToString(", ")
        selectedVegatables.add(text.trim().lowercase())

    }


    private fun addedChip(){

        binding.addChipButton.setOnClickListener {
            if (binding.addChipText.text.isNotEmpty()) {
                val name = binding.addChipText.text.toString().trim()
                createChips(name)
                binding.addChipText.text.clear()
            }
        }

        binding.addChipText.setOnKeyListener { view, i, event ->

            if (i == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP && binding.addChipText.text.isNotEmpty()){

                binding.apply {
                    val name =  addChipText.text.toString().trim()
                    createChips(name)
                    addChipText.text.clear()
                }

                return@setOnKeyListener true
            }
            false
        }

    }

    private fun createChips(name: String){
        val chip = Chip(requireContext())

        chip.apply {

            text = name
            chipIcon = ContextCompat.getDrawable(requireActivity(),
                R.drawable.ic_launcher_background
            )

            isChipIconVisible = false
            isCloseIconVisible = true
            isClickable = true
            isCheckable = true

            binding.apply {
                addedChipGroup.addView(chip as View)
                chip.setOnCloseIconClickListener {
                    addedChipGroup.removeView(chip as View)
                }
            }

        }

    }


    private fun replaceFragment(fragment : Fragment){


        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.nav_host_fragment,fragment)
        fragmentTransaction?.commit()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

