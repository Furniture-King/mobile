package com.example.app.ui.data.models

import com.google.gson.annotations.SerializedName
import java.util.*
import org.bson.types.ObjectId
import java.io.Serializable


var productList = mutableListOf<Product>()
var listProductBasket = mutableListOf<Product>()

val PRODUCT_ID_EXTRA = "productExtra"


data class Product(
    var id: ObjectId,
    val categoryId: Int,
    val name: String,
    val color: String,
    val stock: Int,
    @SerializedName("star")
    val stars: Float,
    val width: Int,
    val category: Int,
    val length: Int, // val height: Int
    val price: Float,
    val description: String,
    val desc1: String,
    val desc2: String,
    val createdAt: Date,
    val updatedAt: Date,
):Serializable
