package com.example.hospitalapp.HR.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.example.hospitalapp.R
import com.example.hospitalapp.Shared.SharedPrefrence
import com.example.hospitalapp.databinding.FragmentCalenderBinding

class CalenderFragment : Fragment() {
    var binding:FragmentCalenderBinding?=null
    var navController:NavController?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {
        binding=FragmentCalenderBinding.inflate(inflater)
        navController=Navigation.findNavController(container!!)

        binding!!.calender.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val myDate = "$year-${month + 1}-$dayOfMonth"
            SharedPrefrence.setUserDate(myDate)
            navController!!.navigate(R.id.action_calenderFragment_to_newUserFragment)
        }



        return binding!!.root
    }

}