package com.example.hospitalapp.HR.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.hospitalapp.HR.ViewModel.HrViewModel
import com.example.hospitalapp.R
import com.example.hospitalapp.Shared.SharedPrefrence
import com.example.hospitalapp.databinding.FragmentProfileBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    var binding:FragmentProfileBinding?=null
    var navController:NavController?=null
    var hrViewModel = HrViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {
        binding=FragmentProfileBinding.inflate(inflater)
        navController=Navigation.findNavController(container!!)
        hrViewModel= ViewModelProviders.of(this).get(HrViewModel::class.java)

        binding!!.btnBack.setOnClickListener {

            navController!!.navigate(R.id.action_profileFragment_to_employeeListFragment)

        }
        GlobalScope.launch(Dispatchers.Main)
        {
            showProfile(SharedPrefrence.getUserid().toString())
        }

        return binding!!.root
    }

    suspend fun showProfile(id:String){
        hrViewModel.ShowProfile(id)

        hrViewModel.mutableShowProfile.observe(viewLifecycleOwner, Observer {

            Log.e("TAG", "showProfile: "+it.message)
            if(it.status==1)
            {
                binding!!.tvDate.text = it.data.birthday
                binding!!.tvEmail.text = it.data.email
                binding!!.tvGender.text = it.data.gender
                binding!!.tvStatues.text = it.data.status
                binding!!.tvName.text = it.data.first_name
                binding!!.tvLocation.text = it.data.address
                binding!!.tvPhone.text = it.data.mobile
                binding!!.tvSpecialist.text = it.data.specialist
            }

        })


    }



}