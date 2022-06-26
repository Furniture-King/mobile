package com.example.app.ui

import com.example.app.ui.api.models.*

// The shopping cart
var SHOPPING_CART : Basket? = null
// The user
var user: Client? = null;
// The jwt response
var JWT : JwtResponse? = null
// The  credit card list
var LIST_CREDIT_CARD = mutableListOf<CreditCard>()
// The credit card ID EXTRA
var CREDIT_CARD_ID_EXTRA = "CreditCardExtra"
// Product ID EXTRA
val PRODUCT_ID_EXTRA = "productExtra"
// The list of all product
var LIST_ALL_PRODUCT = mutableListOf<Product>()
// The list of all product sort by category
var LIST_ALL_PRODUCT_SORT_BY_CATEGORY = mutableListOf<Product>()
// The product shopping cart list
var LIST_PRODUCT_SHOPPING_CART = mutableListOf<Product>()
// The product favourite list
var LIST_PRODUCT_FAVOURITE = mutableListOf<Product>()
// Total price UwU
var TOTAL_PRICE_SHOPPING_CART: Float = 0f