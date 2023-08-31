package com.mecdev.recipie.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mecdev.recipie.R
import com.mecdev.recipie.databinding.ActivityMainBinding
import com.mecdev.recipie.presentation.ui.FavoritesFragment
import com.mecdev.recipie.presentation.ui.FindRecipeFragment
import com.mecdev.recipie.presentation.ui.FindRecipeFragmentDirections
import com.mecdev.recipie.presentation.ui.FragmentContainer
import com.mecdev.recipie.presentation.ui.ProfileFragment


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_app_bar)
        //binding.bottomAppBar.setupWithNavController(findNavController(R.id.nav_host_fragment))

        binding.bottomAppBar.setOnItemSelectedListener {

            when(it.itemId){

                R.id.homeMenuItem -> replaceFragment(FragmentContainer())
                R.id.findRecipeMenuItem -> replaceFragment(FindRecipeFragment())
                R.id.favoritesMenuItem -> replaceFragment(FavoritesFragment())
                R.id.profileMenuItem -> replaceFragment(ProfileFragment())

                else ->{

                }
            }
            true
        }

    }


    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment,fragment)
        fragmentTransaction.commit()
    }
}