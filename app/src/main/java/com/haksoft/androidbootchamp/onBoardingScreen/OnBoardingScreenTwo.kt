package com.haksoft.androidbootchamp.onBoardingScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.haksoft.androidbootchamp.R
import com.haksoft.androidbootchamp.databinding.FragmentOnBoardingScreenTwoBinding

class OnBoardingScreenTwo : Fragment() {


    private lateinit var binding: FragmentOnBoardingScreenTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOnBoardingScreenTwoBinding.inflate(inflater,container,false)
        val buttonToLastOnBoarding : Button = binding.buttonSecondOnBoarding
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        buttonToLastOnBoarding.setOnClickListener {
            viewPager?.currentItem = 2
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}