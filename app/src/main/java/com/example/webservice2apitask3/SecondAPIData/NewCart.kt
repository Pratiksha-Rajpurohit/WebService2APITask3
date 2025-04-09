package com.example.webservice2apitask3.SecondAPIData

data class NewCart(
    var id: Int,
    var userId: Int,
    var products : ArrayList<CartProducts>
)
