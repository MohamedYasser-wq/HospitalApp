package com.example.hospitalapp.HR.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.hospitalapp.R
import com.example.hospitalapp.databinding.FragmentHomeHrBinding

class HomeHrFragment : Fragment() {
   var binding:FragmentHomeHrBinding??=null
    var  navController: NavController?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {
        binding= FragmentHomeHrBinding.inflate(inflater)
        navController= Navigation.findNavController(container!!)


        binding!!.cardView.setOnClickListener {

            navController!!.navigate(R.id.action_homeHrFragment_to_profileFragment)
        }


        binding!!.CardEmployee.setOnClickListener {

            navController!!.navigate(R.id.action_homeHrFragment_to_employeeListFragment)
        }


        return binding!!.root
    }

}