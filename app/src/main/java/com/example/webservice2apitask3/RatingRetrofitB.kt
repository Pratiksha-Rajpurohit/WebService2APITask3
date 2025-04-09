package com.example.webservice2apitask3

import com.example.webservice2apitask3.FirstAPIData.APIResponse
import com.example.webservice2apitask3.FirstAPIData.ProductPOST
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RatingRetrofitB {

    @GET("/products")
    suspend  fun getRatingData(): ArrayList<APIResponse>

    @POST("/products")
    suspend fun postRatingData(@Body product : ProductPOST) : ProductPOST

    @GET("/products/{id}")
    suspend fun getRatingData1(@Path("id") id : Int): APIResponse

    @PUT("/products/{id}")
    suspend fun updateProductData(@Path("id") id : Int , @Body product : ProductPOST)  : ProductPOST

    @DELETE("/products/{id}")
    suspend fun deleteProductData(@Path("id") id: Int)

    companion object{
        fun getInstance() : RatingRetrofitB{
            val retrofit = Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val instance = retrofit.create(RatingRetrofitB::class.java)
            return instance
        }
    }

}