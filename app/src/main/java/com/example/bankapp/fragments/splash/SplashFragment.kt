package com.example.bankapp.fragments.splash

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.bankapp.R
import com.example.bankapp.databinding.FragmentSplashBinding

@Suppress("DEPRECATION")
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)


        animateImage()
        animateName()
        animateSlogan()
        animateDesigner()

        Handler().postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        },2500)

        return binding.root
    }

    private fun animateImage() {
        val animator = ObjectAnimator.ofFloat(binding.logo, View.TRANSLATION_Y, 200f)

        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.duration = 1000
        animator.start()
    }

    private fun animateName() {
        val animator = ObjectAnimator.ofFloat(binding.appName, View.TRANSLATION_Y, 200f)

        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.duration = 1000
        animator.start()
    }

    private fun animateSlogan() {
        val animator = ObjectAnimator.ofFloat(binding.slogan, View.TRANSLATION_Y, -200f)

        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.duration = 1000
        animator.start()
    }

    private fun animateDesigner() {
        val animator = ObjectAnimator.ofFloat(binding.designer, View.TRANSLATION_Y, -200f)

        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.duration = 1000
        animator.start()
    }

}