package com.example.hospitalapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation


class SplashFragment : Fragment() {
    var  navController: NavController?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View?
    {
        navController= Navigation.findNavController(container!!)


        Handler(Looper.getMainLooper()).postDelayed({

             navController!!.navigate(R.id.action_splashFragment_to_loginFragment)

        }, 3000)

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

}