package com.example.bankapp.fragments.home

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.bankapp.R
import com.example.bankapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        animateButton()

        binding.viewUsersBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_customersFragment)
        }

        return binding.root
    }

    private fun animateButton(){
        val animator = ObjectAnimator.ofFloat(binding.viewUsersBtn, View.TRANSLATION_Y, -200f)
        animator.duration = 500
        animator.start()
    }

}