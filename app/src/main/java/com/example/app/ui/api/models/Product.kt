package com.example.app.ui.api.models

import java.io.Serializable
import kotlin.collections.ArrayList

// Product ID EXTRA
val PRODUCT_ID_EXTRA = "productExtra"
// Category ID EXTRA
val CATEGORY_ID_EXTRA = "categoryProductExtra"
// The product list
var LIST_ALL_PRODUCT = mutableListOf<Product>()
// The product shopping cart list
var LIST_PRODUCT_SHOPPING_CART = mutableListOf<Product>()
// The product favourite list
var LIST_PRODUCT_FAVOURITE = mutableListOf<Product>()
// Total price UwU
var TOTAL_PRICE_SHOPPING_CART: Float = 0f

/**
 * Meaning of a article
 */
data class Product(
    val id: String?,
    val categoryName: String?,
    val name: String?,
    val color: String?,
    val srcImg: ArrayList<String?>,
    val stock: Int?,
    val stars: Float?,
    val width: Float?,
    val length: Float?,
    val price: Float?,
    val description: String?,
    val desc1: String?,
    val desc2: String?,
    val createdAt: Long?,
    val updatedAt: Long?,
) : Serializable
