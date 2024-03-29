package com.haksoft.androidbootchamp.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import com.haksoft.androidbootchamp.Activity.MainActivity

import com.haksoft.androidbootchamp.databinding.FragmentChangeUserBinding

class ChangeUserFragment : Fragment() {

    private lateinit var binding: FragmentChangeUserBinding
    var radioButtonChoice : Int = 0
    private lateinit var preferences : SharedPreferences
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentChangeUserBinding.inflate(inflater,container,false)
        val toolBarChangingUser : Toolbar = binding.toolBarChangingUser
        val buttonKaydet : Button = binding.buttonKaydet
        preferences = requireActivity().getSharedPreferences("com.haksoft.androidbootchamp", Context.MODE_PRIVATE)
        (activity as MainActivity).setSupportActionBar(toolBarChangingUser)

        binding.TextInputeditTextName.setText(preferences?.getString("user", ""))
        binding.radioGroupChangingUser.check(binding.radioGroupChangingUser.getChildAt(preferences?.getInt("gender", 2)).getId());
        radioButtonChoice = preferences?.getInt("gender", 2);

        binding.radioGroupChangingUser.setOnCheckedChangeListener { group, checkedId ->

            var radioButton:View = binding.radioGroupChangingUser.findViewById(checkedId)
            var index = binding.radioGroupChangingUser.indexOfChild(radioButton)
            when(index){
                0 -> radioButtonChoice = 0
                1 -> radioButtonChoice = 1
                2 -> radioButtonChoice = 2
            }
        }
        buttonKaydet.setOnClickListener {


            val takeName = binding.TextInputeditTextName.text.toString()
            preferences?.edit()?.putString("user",takeName)?.apply()
            preferences?.edit()?.putInt("gender",radioButtonChoice)?.apply()



            val action = ChangeUserFragmentDirections.actionChangeUserFragmentToMainFragment()
            Navigation.findNavController(binding.root).navigate(action)

        }
        return binding.root

    }


}