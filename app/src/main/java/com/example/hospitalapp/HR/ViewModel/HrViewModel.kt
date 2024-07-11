package com.example.hospitalapp.HR.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hospitalapp.Data.AllUser
import com.example.hospitalapp.Data.UserData

class HrViewModel: ViewModel()  {

    val mutableRegister= MutableLiveData<UserData>()
    val mutableShowProfile= MutableLiveData<UserData>()
    val mutableGetAllUser= MutableLiveData<AllUser>()


    suspend fun Register(email:String,Pass:String,firstname:String,lastname:String,gender:String,
                         specialist:String,birthDay:String,statues:String,
                         address:String,mobile:String,type:String){

        val response=RetroConnection.api.Register(email,Pass,firstname,lastname,gender,specialist,birthDay,statues,address,mobile,type)
        mutableRegister.postValue(response)

    }

    suspend fun ShowProfile(id:String){

        val response=RetroConnection.api.ShowProfile(id)
        mutableShowProfile.postValue(response)

    }

    suspend fun GetAllUser(type:String){

        val response=RetroConnection.api.GetAllUser(type)
        mutableGetAllUser.postValue(response)

    }
}