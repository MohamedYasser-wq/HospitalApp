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
import com.example.hospitalapp.Adapter.RecAllUser
import com.example.hospitalapp.Adapter.RecyclerSpecialist
import com.example.hospitalapp.HR.ViewModel.HrViewModel
import com.example.hospitalapp.R
import com.example.hospitalapp.Shared.SharedPrefrence
import com.example.hospitalapp.databinding.FragmentEmployeeListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EmployeeListFragment : Fragment(), RecyclerSpecialist.OnClick,RecAllUser.OnClick{

    private var binding: FragmentEmployeeListBinding? = null
    private var list = ArrayList<String>()
    private lateinit var recyclerSpecialist: RecyclerSpecialist
    private lateinit var recyclerAllUser: RecAllUser
    private var navController: NavController? = null
    var hrViewModel = HrViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmployeeListBinding.inflate(inflater, container, false)
        navController = Navigation.findNavController(container!!)
        recyclerSpecialist = RecyclerSpecialist(this)
        recyclerAllUser = RecAllUser(this)
        hrViewModel= ViewModelProviders.of(this).get(HrViewModel::class.java)

        fillList()

        binding!!.btnBack.setOnClickListener {
            navController!!.navigate(R.id.action_employeeListFragment_to_homeHrFragment)
        }

        binding!!.BtnAdd.setOnClickListener {
            navController!!.navigate(R.id.action_employeeListFragment_to_newUserFragment)
        }

        return binding!!.root
    }

    private fun fillList() {
        list.add("All")
        list.add("Doctor")
        list.add("Nurse")
        list.add("HR")
        list.add("Analysis")
        list.add("manager")
        list.add("receptionist")
        recyclerSpecialist.setList(list)
        binding!!.RecSpecialist.adapter = recyclerSpecialist
    }

    override fun getType(type: String) {
        Log.e("TAG", "getType: "+type)

        GlobalScope.launch(Dispatchers.Main) {
            ShowAllUser(type)
        }

    }

    private suspend fun ShowAllUser(type: String) {

        hrViewModel.GetAllUser(type)
        hrViewModel.mutableGetAllUser.observe(viewLifecycleOwner, Observer {

            Log.e("TAG", "ShowAllUser: "+it)
            recyclerAllUser.setListtt(it)
            binding!!.recSelectedUsers.adapter=recyclerAllUser


        })

    }

    override fun getId(id: Int) {
        SharedPrefrence.setUserid(id.toString())
        navController!!.navigate(R.id.action_employeeListFragment_to_profileFragment)

    }


}
