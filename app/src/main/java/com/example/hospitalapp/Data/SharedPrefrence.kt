package com.example.hospitalapp.Shared

import android.content.Context
import android.content.SharedPreferences


object SharedPrefrence {
    private const val PREFS_NAME = "user_data"
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun setUserToken (id : String){
        with(sharedPreferences.edit() ){
            putString("USER_TOKEN", id)
            commit()
        }
    }
    fun getUserToken(): String? {
        return sharedPreferences.getString("USER_TOKEN", "" )
    }

    fun setUserType (id : String){
        with(sharedPreferences.edit() ){
            putString("USER_TYPE", id)
            commit()
        }
    }
    fun getUserType(): String? {
        return sharedPreferences.getString("USER_TYPE", "" )
    }
    fun setUserName(id : String){
        with(sharedPreferences.edit() ){
            putString("USER_NAME", id)
            commit()
        }
    }
    fun getUserName(): String? {
        return sharedPreferences.getString("USER_NAME", "" )
    }

    fun setUserphone(id : String){
        with(sharedPreferences.edit() ){
            putString("USER_phone", id)
            commit()
        }
    }
    fun getUserphone(): String? {
        return sharedPreferences.getString("USER_phone", "" )
    }


    fun setUserDate(Date : String){
        with(sharedPreferences.edit() ){
            putString("USER_Date", Date)
            commit()
        }
    }
    fun getUserDate(): String? {
        return sharedPreferences.getString("USER_Date", "" )
    }

    fun setUserid(id : String){
        with(sharedPreferences.edit() ){
            putString("USER_id", id)
            commit()
        }
    }
    fun getUserid(): String? {
        return sharedPreferences.getString("USER_id", "" )
    }


}