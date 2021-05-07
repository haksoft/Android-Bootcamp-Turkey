package com.haksoft.androidbootchamp.onBoardingScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.haksoft.androidbootchamp.databinding.FragmentViewPagerBinding

class ViewPagerFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewPagerBinding.inflate(inflater,container,false)
        val fragmentList = arrayListOf<Fragment>(
            OnBoardingScreenOne(),
            OnBoardingScreenTwo(),
            OnBoardingScreenLast()

        )
        val adapter = ViewPagerAdapter(fragmentList,requireActivity().supportFragmentManager,lifecycle)
        binding.viewPager.adapter =  adapter
        return binding.root
    }


}