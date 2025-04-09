package com.example.webservice2apitask3

import android.util.Log
import com.example.webservice2apitask3.SecondAPIData.AllCarts
import com.example.webservice2apitask3.SecondAPIData.CartProducts
import com.example.webservice2apitask3.SecondAPIData.NewCart
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class SecondAPI {

    fun gsonResponse(){
        CoroutineScope(Dispatchers.IO).launch {
            var url = URL("https://fakestoreapi.com/carts")
            var httpsURLConnection = url.openConnection() as HttpsURLConnection
            httpsURLConnection.connect()

            val inStream = httpsURLConnection.inputStream

            val inputStreamReader = InputStreamReader(inStream)
            val list = ArrayList<AllCarts>()
            val apiResponse = Gson().fromJson(inputStreamReader, list::class.java)

            Log.e("tag", "${apiResponse}")

        }
    }

    fun retrofitResponse(){

        CoroutineScope(Dispatchers.IO).launch {

            val cartsRetrofit = CartsRetrofitB.getInstance()
            val cartsData = cartsRetrofit.getAllCarts()

            Log.d("carts", cartsData.toString())

            val singleCart = cartsRetrofit.getSingleCart(1)

            Log.d("carts", singleCart.toString())

            val newCart = NewCart(
                2,
                23,
                arrayListOf(
                    CartProducts(
                        1,
                        2
                    )
                )
            )

            val postNewCart = cartsRetrofit.postNewCart(newCart)
            Log.d("carts", postNewCart.toString())

            val putCart = NewCart(
                5,
                3,
                arrayListOf(
                    CartProducts(
                        1,
                        2
                    )
                )
            )

            val updateCart = cartsRetrofit.updateCart(5, putCart)
            Log.d("carts", updateCart.toString())

            cartsRetrofit.deleteCart(5)
            Log.d("carts", "deleted")


        }

    }


}