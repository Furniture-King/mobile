package com.example.app.ui.api.models


/**
 * The shopping cart
 */
var SHOPPING_CART : Basket? = null

/**
 * Meaning of a shopping cart
 */
data class Basket (
    val basketCartId: String?,
    val client: Client?,
    val basketTab: MutableList<ShoppingCartItem>?,
    var basketTotalPrice: Float?,
    val createdAt: Long?,
    val updatedAt: Long?,
)