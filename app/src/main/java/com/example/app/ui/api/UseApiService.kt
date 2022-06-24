package com.example.app.ui.api

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.app.ui.api.models.*
import com.example.app.ui.util.AddToList
import com.example.app.ui.util.showHide
import kotlinx.coroutines.*
import java.net.HttpURLConnection


// Get the shopping cart
fun order() {
    //initiate the service
    CoroutineScope(Dispatchers.IO).launch {
        val res =
            USE_API.getShoppingCart(
                "Bearer " + JWT!!.accessToken,
                JWT!!.id
            )
        if (res?.code() == HttpURLConnection.HTTP_OK) {
            println(res.body())
            println(res.body()?.toString())
            SHOPPING_CART = res.body()
            if (SHOPPING_CART?.basketTab!! != null) {

                var listShoppingCartItemProduct: MutableList<Product> = mutableListOf()
                for (shoppingCartItem in SHOPPING_CART?.basketTab!!) {
                    listShoppingCartItemProduct.add(shoppingCartItem.product!!)
                }
                LIST_PRODUCT_SHOPPING_CART = listShoppingCartItemProduct

            }
            TOTAL_PRICE_SHOPPING_CART = SHOPPING_CART?.basketTotalPrice!!
        }
        println(res)
    }
}

// Get the shopping cart
fun getShoppingCart() {
    //initiate the service
    CoroutineScope(Dispatchers.IO).launch {
        val res =
            USE_API.getShoppingCart(
                "Bearer " + JWT!!.accessToken,
                JWT!!.id
            )
        if (res?.code() == HttpURLConnection.HTTP_OK) {
            println(res.body())
            println(res.body()?.toString())
            SHOPPING_CART = res.body()
            if (SHOPPING_CART?.basketTab!! != null) {

                var listShoppingCartItemProduct: MutableList<Product> = mutableListOf()
                for (shoppingCartItem in SHOPPING_CART?.basketTab!!) {
                    listShoppingCartItemProduct.add(shoppingCartItem.product!!)
                }
                LIST_PRODUCT_SHOPPING_CART = listShoppingCartItemProduct

            }
            TOTAL_PRICE_SHOPPING_CART = SHOPPING_CART?.basketTotalPrice!!
        }
        println(res)
    }
}

fun addProductShoppingCart(product: Product, imgCart: Array<ImageView>?) {

    if (JWT?.id == null) {
        TOTAL_PRICE_SHOPPING_CART += product.price!!
        LIST_PRODUCT_SHOPPING_CART.add(product)
        if (imgCart != null) {
            showHide(imgCart)
        }
    } else {
        //initiate the service
        val destinationService = ServiceBuilder.buildService(ApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val res =
                destinationService.addProductShoppingCart(
                    "Bearer " + JWT!!.accessToken,
                    JWT!!.id,
                    ShoppingCartItem(product, 1, product.price)
                )
            if (res?.code() == HttpURLConnection.HTTP_OK) {
                println(res?.body()?.string())
                AddToList(LIST_PRODUCT_SHOPPING_CART, product)
                GlobalScope.launch {
                    //You can use for background procces
                    withContext(Dispatchers.Main) {
                        if (imgCart != null) {
                            showHide(imgCart)
                        }
                    }
                }
            }
            println(res)
        }
    }
}

fun removeProductShoppingCart(product: Product, imgCart: Array<ImageView>?) {

    if (JWT?.id == null) {
        TOTAL_PRICE_SHOPPING_CART -= product.price!!
        LIST_PRODUCT_SHOPPING_CART.remove(product)
        if (imgCart != null) {
            showHide(imgCart)
        }
    } else {
        //initiate the service
        val destinationService = ServiceBuilder.buildService(ApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val res = destinationService.removeProductShoppingCart(
                "Bearer " + JWT!!.accessToken,
                product.id!!,
                JWT!!.id
            )

            if (res?.code() == HttpURLConnection.HTTP_OK) {
                LIST_PRODUCT_SHOPPING_CART.remove(product)
                GlobalScope.launch {
                    //You can use for background procces
                    withContext(Dispatchers.Main) {
                        if (imgCart != null) {
                            showHide(imgCart)
                        }
                    }
                }
            }
            println(res)
        }
    }
}

fun populateProducts(): LiveData<MutableList<Product>> {
    val liveProduct = MutableLiveData<MutableList<Product>>()
    //initiate the service
    val destinationService = ServiceBuilder.buildService(ApiService::class.java)
//        val requestCall = destinationService.getProductList()
    //make network call asynchronously

    CoroutineScope(Dispatchers.IO).launch {

        val res = destinationService.getProductList()

        if (res.code() == HttpURLConnection.HTTP_OK) {
            LIST_ALL_PRODUCT = res?.body()!!.toMutableList()
            Log.d("Response", "product size : ${LIST_ALL_PRODUCT.size}")
            liveProduct.postValue(LIST_ALL_PRODUCT)
        }
    }
    return liveProduct
}
