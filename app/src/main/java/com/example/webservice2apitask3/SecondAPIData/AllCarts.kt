package com.example.webservice2apitask3.SecondAPIData

import com.google.gson.annotations.SerializedName

data class AllCarts(
    var id: Int,
    var userId: Int,
    var date : String,
    var products : ArrayList<CartProducts>,
    @SerializedName("__v")
    var v : Int
)