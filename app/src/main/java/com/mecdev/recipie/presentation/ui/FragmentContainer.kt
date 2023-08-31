package com.mecdev.recipie.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import com.mecdev.recipie.R
import com.mecdev.recipie.databinding.FragmentContainerBinding

class FragmentContainer : Fragment() {

    private var _binding: FragmentContainerBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContainerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





    }

  /*  private fun replaceFragment(fragment: Fragment){
        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        // Replace the existing fragment with the new one
        fragmentTransaction.replace(R.id.containerFrameLayout,fragment)
        fragmentTransaction.commit()

    }
   */


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}