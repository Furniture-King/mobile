package com.example.app.ui.api.models

import java.util.*
import org.bson.types.ObjectId
import java.io.Serializable

/**
 * The product list
 */
var productList = mutableListOf<Product>()
/**
 * The product shopping cart list
 */
var listProductShoppingCart = mutableListOf<Product>()
/**
 * The product favourite list
 */
var listProductFavourite = mutableListOf<Product>()

/**
 * Product ID EXTRA
 */
val PRODUCT_ID_EXTRA = "productExtra"

/**
 * Category ID EXTRA
 */
val CATEGORY_ID_EXTRA = "categoryProductExtra"


/**
 * Meaning of a article
 */
data class Product(
    var id: ObjectId,
    var categoryName: String?,
    val name: String?,
    val color: String?,
    val srcImg: String?,
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
):Serializable
