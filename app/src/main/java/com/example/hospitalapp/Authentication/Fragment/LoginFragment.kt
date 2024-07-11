package com.example.hospitalapp.Authentication.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.hospitalapp.Authentication.ViewModel.AuthenticationViewModel
import com.example.hospitalapp.R
import com.example.hospitalapp.Shared.SharedPrefrence
import com.example.hospitalapp.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
     var binding:FragmentLoginBinding?=null
    var  navController:NavController?=null
    var authenticationViewModel = AuthenticationViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {
        binding=FragmentLoginBinding.inflate(inflater)
        navController=Navigation.findNavController(container!!)
        authenticationViewModel= ViewModelProviders.of(this).get(AuthenticationViewModel::class.java)


    binding!!.btnLogin.setOnClickListener {

       val email=binding!!.EdtEmail.text.toString()
       val pass=binding!!.EdtPassword.text.toString()

        GlobalScope.launch(Dispatchers.Main){
            validate(email,pass)

        }


    }

        return binding!!.root
    }

     suspend fun  validate(email:String,Pass:String){

        if (email.isEmpty()) {
            Toast.makeText(requireContext(), "Please Enter Email", Toast.LENGTH_SHORT).show()

        }

        if (!email.contains("@")) {
            Toast.makeText(requireContext(), "Please Enter a Valid Email", Toast.LENGTH_SHORT).show()

        }

        if (Pass.isEmpty()) {
            Toast.makeText(requireContext(), "Please Enter Password", Toast.LENGTH_SHORT).show()

        }

        if (Pass.length < 6) {
            Toast.makeText(requireContext(), "Password Must Be at Least 6 Characters", Toast.LENGTH_SHORT).show()

        }

       Login(email, Pass)
    }

private suspend fun Login(email: String, pass: String) {

    authenticationViewModel.login(email,pass)

    authenticationViewModel.mutableLogin.observe(viewLifecycleOwner, Observer {

    if(it.status.equals(1)){

        Toast.makeText(requireContext(), "Login Successfully", Toast.LENGTH_SHORT).show()
        SharedPrefrence.setUserToken(it.data.access_token)
        SharedPrefrence.setUserid(it.data.id.toString())

        navController!!.navigate(R.id.action_loginFragment_to_homeHrFragment)


    }
    else
        Toast.makeText(requireContext(), "Something Wrong!!", Toast.LENGTH_SHORT).show()



  })



    }


}



