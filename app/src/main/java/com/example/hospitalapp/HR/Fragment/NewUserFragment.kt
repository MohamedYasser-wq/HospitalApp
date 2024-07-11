package com.example.hospitalapp.HR.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.hospitalapp.HR.ViewModel.HrViewModel

import com.example.hospitalapp.R
import com.example.hospitalapp.Shared.SharedPrefrence
import com.example.hospitalapp.databinding.FragmentNewUserBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewUserFragment : Fragment() {
    private var binding: FragmentNewUserBinding? = null
    private var navController: NavController? = null
    private val Genderlist = arrayOf("Gender", "male", "female")
    private val Statuslist = arrayOf("Status", "single", "married")
    private val Specialistlist = arrayOf("specialist","doctor", "hr", "receptionist ","Analysis", "manger", "Nurse")
    val Gender:String=""
    val specialist:String=""
    val statues:String=""
    var hrViewModel = HrViewModel()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentNewUserBinding.inflate(inflater, container, false)
        navController = Navigation.findNavController(container!!)
        hrViewModel= ViewModelProviders.of(this).get(HrViewModel::class.java)

        SetAllSpinner()



        binding!!.EdtDate.setOnClickListener {

            navController!!.navigate(R.id.action_newUserFragment_to_calenderFragment)
        }

        binding?.spinnerGender?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position > 0) { // Avoid setting the "Gender" option as the text
                    binding?.EdtGender?.setText(Genderlist[position])
                } else {
                    binding?.EdtGender?.setText("")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                binding?.EdtGender?.setText("Please Select Gender")
            }
        }


        binding?.spinnerStatus?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position > 0) { // Avoid setting the "Gender" option as the text
                    binding?.EdtStatus?.setText(Statuslist[position])
                } else {
                    binding?.EdtStatus?.setText("")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                binding?.EdtGender?.setText("Please Select Statues")
            }
        }


        binding?.spinnerSpecialist?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position > 0) { // Avoid setting the "Gender" option as the text
                    binding?.EdtSpecialist?.setText(Specialistlist[position])
                } else {
                    binding?.EdtSpecialist?.setText("")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                binding?.EdtGender?.setText("Please Select Specialist")
            }
        }

        binding?.btnBack?.setOnClickListener {
            navController?.navigate(R.id.action_newUserFragment_to_employeeListFragment)
        }



        binding!!.btnAddUser.setOnClickListener {
            var firstName=binding!!.EdtFirstName.text.toString()
            var lastName=binding!!.EdtLastName.text.toString()
            var gender=binding!!.EdtGender.text.toString()
            var specialist=binding!!.EdtSpecialist.text.toString()
            var date=binding!!.EdtDate.text.toString()
            var statues=binding!!.EdtStatus.text.toString()
            var phone=binding!!.edtPhoneNumber.text.toString()
            var address=binding!!.edtAddress.text.toString()
            var pass=binding!!.edtPass.text.toString()
            var email=binding!!.edtEmail.text.toString()

            GlobalScope.launch(Dispatchers.Main) {


                Validation(
                    firstName,
                    lastName,
                    gender,
                    specialist,
                    date,
                    statues,
                    phone,
                    address,
                    pass,
                    email
                )
            }
        }


        return binding?.root
    }

    private suspend fun Validation(
        firstName: String,
        lastName: String,
        gender: String,
        specialist: String,
        date: String,
        statues: String,
        phone: String,
        address: String,
        pass: String,
        email: String
    )
    {

        when {
            firstName.isEmpty() -> {
                Toast.makeText(requireContext(), "Please Enter First Name", Toast.LENGTH_SHORT).show()

            }
            lastName.isEmpty() -> {
                Toast.makeText(requireContext(), "Please Enter Last Name", Toast.LENGTH_SHORT).show()
                     }
            gender.isEmpty() -> {
                Toast.makeText(requireContext(), "Please Select Gender", Toast.LENGTH_SHORT).show()

            }
            specialist.isEmpty() -> {
                Toast.makeText(requireContext(), "Please Enter Specialist", Toast.LENGTH_SHORT).show()

            }
            date.isEmpty() -> {
                Toast.makeText(requireContext(), "Please Enter Date", Toast.LENGTH_SHORT).show()

            }
            statues.isEmpty() -> {
                Toast.makeText(requireContext(), "Please Select Status", Toast.LENGTH_SHORT).show()

            }
            phone.isEmpty() -> {
                Toast.makeText(requireContext(), "Please Enter Phone Number", Toast.LENGTH_SHORT).show()

            }
            address.isEmpty() -> {
                Toast.makeText(requireContext(), "Please Enter Address", Toast.LENGTH_SHORT).show()

            }
            pass.isEmpty() -> {
                Toast.makeText(requireContext(), "Please Enter Password", Toast.LENGTH_SHORT).show()

            }
            pass.length < 6 -> {
                Toast.makeText(requireContext(), "Password Must Be at Least 6 Characters", Toast.LENGTH_SHORT).show()

            }
            email.isEmpty() -> {
                Toast.makeText(requireContext(), "Please Enter Email", Toast.LENGTH_SHORT).show()

            }
            !email.contains("@") -> {
                Toast.makeText(requireContext(), "Please Enter a Valid Email", Toast.LENGTH_SHORT).show()

            }
            else -> {
                AddNewUser(firstName,lastName,gender,specialist,statues,phone,pass,email,address,date)
            }
        }
    }

    private suspend fun AddNewUser(
        firstName: String,
        lastName: String,
        gender: String,
        specialist: String,
        statues: String,
        phone: String,
        pass: String,
        email: String,
        address: String,
        date: String
    )

    {
        hrViewModel.Register(email,pass,firstName,lastName,gender,specialist,date,statues,address,phone,specialist)

        hrViewModel.mutableRegister.observe(viewLifecycleOwner, Observer {
            Log.e("TAG", "AddNewUser: "+it.message)
            Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()



        })
    }


    private fun SetAllSpinner(){

        setupSpinnerSpecialist()
        setupSpinnerGender()
        setupSpinnerStatus()
        binding!!.EdtDate.setText(SharedPrefrence.getUserDate())
    }


    private fun setupSpinnerGender() {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, Genderlist)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding?.spinnerGender?.adapter = adapter
    }

    private fun setupSpinnerStatus() {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, Statuslist)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding?.spinnerStatus?.adapter = adapter
    }

    private fun setupSpinnerSpecialist() {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, Specialistlist)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding?.spinnerSpecialist?.adapter = adapter
    }





}
