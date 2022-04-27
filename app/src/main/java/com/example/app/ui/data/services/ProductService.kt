package com.example.app.ui.data.services

import com.example.app.ui.data.models.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductService
{
    fun onClick(product: Product)
    @GET("products")
    fun getProductList () : Call<List<Product>>
}