package com.example.app.ui.api.models

/**
 * Meaning of a product item in the shopping cart
 */
data class ShoppingCartItem(
    var product: Product?,
    var qté: Int?,
    var priceProduct: Float?
)