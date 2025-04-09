package com.example.webservice2apitask3

import com.example.webservice2apitask3.SecondAPIData.AllCarts
import com.example.webservice2apitask3.SecondAPIData.NewCart
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CartsRetrofitB {

    @GET("/carts")
    suspend fun getAllCarts(): ArrayList<AllCarts>

    @GET("/carts/{id}")
    suspend fun getSingleCart(@Path("id") id : Int): AllCarts

    @POST("/carts")
    suspend fun postNewCart(@Body cart: NewCart) : NewCart

    @PUT("/carts/{id}")
    suspend fun updateCart(@Path("id") id : Int , @Body cart : NewCart)  : NewCart

    @DELETE("/carts/{id}")
    suspend fun deleteCart(@Path("id") id: Int)

    companion object{
        fun getInstance() : CartsRetrofitB{
            val retrofit = Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val instance = retrofit.create(CartsRetrofitB::class.java)
            return instance
        }
    }
}