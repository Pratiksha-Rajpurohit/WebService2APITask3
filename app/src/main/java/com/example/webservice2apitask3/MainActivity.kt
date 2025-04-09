package com.example.webservice2apitask3

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.webservice2apitask3.FirstAPIData.APIResponse
import com.example.webservice2apitask3.FirstAPIData.ProductPOST
import com.example.webservice2apitask3.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.button1.setOnClickListener {
            ratingGsonResponse()
        }

        binding.button2.setOnClickListener {
            ratingRetrofitResponse()
        }

        val secondAPI = SecondAPI()

        binding.button3.setOnClickListener {
            secondAPI.gsonResponse()
        }

        binding.button4.setOnClickListener {
            secondAPI.retrofitResponse()
        }


    }

    fun ratingGsonResponse(){
        CoroutineScope(Dispatchers.IO).launch {
                var url = URL("https://fakestoreapi.com/products/1")
                var httpsURLConnection = url.openConnection() as HttpsURLConnection
                httpsURLConnection.connect()

                var inStream = httpsURLConnection.inputStream

                var inputStreamReader = InputStreamReader(inStream)
                var apiResponse = Gson().fromJson(inputStreamReader, APIResponse::class.java)

                Log.e("tag", "${apiResponse}")
        }
    }

    fun ratingRetrofitResponse(){

        CoroutineScope(Dispatchers.IO).launch {

            val ratingRetrofit = RatingRetrofitB.getInstance()
            val ratingData = ratingRetrofit.getRatingData()

            val newUser = ProductPOST(
                1,
                "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                32000.0,
                "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                "any",
                "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
            )

            Log.d("rating", ratingData.toString())

            val postRatingData = ratingRetrofit.postRatingData(newUser)
            Log.d("rating", postRatingData.toString())

            val ratingData1 = ratingRetrofit.getRatingData1(3)
            Log.d("rating", ratingData1.toString())


            val putProduct = ProductPOST(

                1,
                "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                320.0,
                "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
                "any",
                "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
            )

            val updateProduct = ratingRetrofit.updateProductData(1, putProduct)
            Log.d("rating", updateProduct.toString())

            ratingRetrofit.deleteProductData(1)
            Log.d("rating", "deleted")
        }

    }


}