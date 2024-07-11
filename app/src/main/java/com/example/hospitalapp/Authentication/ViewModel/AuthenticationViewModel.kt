package com.example.hospitalapp.Authentication.ViewModel

import RetroConnection
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hospitalapp.Data.AllUser
import com.example.hospitalapp.Data.UserData

class AuthenticationViewModel : ViewModel() {

    val mutableLogin=MutableLiveData<UserData>()


    suspend fun login(email:String,Pass:String){

        val response=RetroConnection.api.login(email,Pass)
        mutableLogin.postValue(response)

    }






}