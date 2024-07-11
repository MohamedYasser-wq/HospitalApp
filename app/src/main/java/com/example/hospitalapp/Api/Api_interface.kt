package com.example.hospitalapp.Api

import com.example.hospitalapp.Data.AllUser
import com.example.hospitalapp.Data.UserData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api_interface {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): UserData


    @FormUrlEncoded
    @POST("register")
    suspend fun Register(

        @Field("email")email:String,
        @Field("password")password:String,
        @Field("first_name")first_name:String,
        @Field("last_name")last_name:String,
        @Field("gender")gender:String,
        @Field("specialist")specialist:String,
        @Field("birthday")birthday:String,
        @Field("status")status:String,
        @Field("address")address:String,
        @Field("mobile")mobile:String,
        @Field("type")type:String,

    ): UserData

    @FormUrlEncoded
    @POST("show-profile")
    suspend fun ShowProfile(
        @Field("user_id") id: String,
        ): UserData



    @GET("doctors")
    suspend fun GetAllUser(@Query("type") type: String):AllUser
}