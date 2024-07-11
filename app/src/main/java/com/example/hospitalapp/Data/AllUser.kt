package com.example.hospitalapp.Data

data class AllUser(
    val `data`: List<AllDataForAllUser>,
    val message: String,
    val status: Int
)

data class AllDataForAllUser(
    val avatar: String,
    val first_name: String,
    val id: Int,
    val type: String
)