package com.example.webservice2apitask3.FirstAPIData

data class APIResponse(
      var id :Int,
      var title : String,
      var price : Double,
      var description : String,
      var category : String,
      var image : String,
      var rating : Rating
)
