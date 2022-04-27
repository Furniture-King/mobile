package com.example.app.ui.data.interfaces

import com.example.app.ui.data.models.Product
import retrofit2.Call
import retrofit2.http.GET

interface ArticleClickListener
{
    fun onClick(article: Product)
//    @GET("countries")
//    fun getAffectedCountryList () : Call<List<Product>>
}