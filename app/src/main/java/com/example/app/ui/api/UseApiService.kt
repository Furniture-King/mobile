package com.example.app.ui.api

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.app.ui.*
import com.example.app.ui.api.models.Product
import com.example.app.ui.api.models.ShoppingCartItem
import com.example.app.ui.util.AddToList
import com.example.app.ui.util.showHide
import kotlinx.coroutines.*
import java.net.HttpURLConnection.HTTP_OK


/**
 * Get all product by category
 *
 * @return a list of product
 */
fun getProductByCategory(category: String): LiveData<MutableList<Product>> {
    val liveData = MutableLiveData<MutableList<Product>>()

    CoroutineScope(Dispatchers.IO).launch {
        val res =
            USE_API.getProductByCategory(category)
        if (res?.code() == HTTP_OK) {
            liveData.postValue(res.body()?.toMutableList())
        }
    }
    return liveData
}

/**
 * Allow to make a order
 *
 * @return a string response
 */
fun order(): LiveData<String> {
    val liveData = MutableLiveData<String>()

    CoroutineScope(Dispatchers.IO).launch {
        val res =
            USE_API.orderProduct(
                "Bearer " + JWT!!.accessToken,
            )
        if (res?.code() == HTTP_OK) {
            liveData.postValue(res.body()?.string())
        }
    }
    return liveData
}

/**
 * Get the shopping cart
 */
fun getShoppingCart() {
    //initiate the service
    CoroutineScope(Dispatchers.IO).launch {
        val res =
            USE_API.getShoppingCart(
                "Bearer " + JWT!!.accessToken,
                JWT!!.id
            )
        if (res?.code() == HTTP_OK) {

            SHOPPING_CART = res.body()
            if (SHOPPING_CART?.basketTab != null) {

                var listShoppingCartItemProduct: MutableList<Product> = mutableListOf()
                for (shoppingCartItem in SHOPPING_CART?.basketTab!!) {
                    listShoppingCartItemProduct.add(shoppingCartItem.product!!)
                }
                LIST_PRODUCT_SHOPPING_CART = listShoppingCartItemProduct

            }
            TOTAL_PRICE_SHOPPING_CART = SHOPPING_CART?.basketTotalPrice!!
        }
    }
}

/**
 * Get the bookmark
 */
fun getBookmark() {
    //initiate the service
    CoroutineScope(Dispatchers.IO).launch {
        val res =
            USE_API.getBookmark(
                "Bearer " + JWT!!.accessToken,
                JWT!!.id
            )
        if (res?.code() == HTTP_OK) {
            LIST_PRODUCT_FAVOURITE = res.body()!!.toMutableList()
        }
    }
}

/**
 * Add a product to the bookmark and change the fav image view visibility
 *
 * @param product The product to add in the bookmark
 */
fun addProductBookmark(product: Product, imgHeart: Array<ImageView>?) {

    if (JWT?.id == null) {
        LIST_PRODUCT_FAVOURITE.add(product)
        if (imgHeart != null) {
            showHide(imgHeart)
        }
    } else {
        CoroutineScope(Dispatchers.IO).launch {
            val res =
                USE_API.addProductBookmark(
                    "Bearer " + JWT!!.accessToken,
                    JWT!!.id,
                    product
                )
            if (res?.code() == HTTP_OK) {
                getBookmark()
                GlobalScope.launch {
                    //You can use for background procces
                    withContext(Dispatchers.Main) {
                        if (imgHeart != null) {
                            showHide(imgHeart)
                        }
                    }
                }
            }
        }
    }
}

/**
 * Remove a product to the bookmark and change the fav image view visibility
 *
 * @param product The product to remove in the bookmark
 */
fun removeProductBookmark(product: Product, imgHeart: Array<ImageView>?) {

    if (JWT?.id == null) {
        LIST_PRODUCT_FAVOURITE.remove(product)
        if (imgHeart != null) {
            showHide(imgHeart)
        }
    } else {

        CoroutineScope(Dispatchers.IO).launch {
            val res =
                USE_API.removeProductBookmark(
                    "Bearer " + JWT!!.accessToken,
                    product.id,
                    JWT!!.id,
                )
            if (res?.code() == HTTP_OK) {
                getBookmark()
                GlobalScope.launch {
                    //You can use for background procces
                    withContext(Dispatchers.Main) {
                        if (imgHeart != null) {
                            showHide(imgHeart)
                        }
                    }
                }
            }
        }
    }
}

/**
 * Put a product to the shopping cart and change the shopping card image view visibility
 *
 * @param product The product to put in the shopping cart
 * @param newQte quantity of the product
 */
fun putProductShoppingCart(product: Product, newQte: Int) {

    if (JWT?.id == null) {
        return
    } else {
        //initiate the service
        CoroutineScope(Dispatchers.IO).launch {
            val res =
                USE_API.addProductShoppingCart(
                    "Bearer " + JWT!!.accessToken,
                    JWT!!.id,
                    ShoppingCartItem(product, newQte)
                )
            if (res?.code() == HTTP_OK) {
                getShoppingCart()
            }
        }
    }
}

/**
 * Add a product to the shopping cart and change the shopping card image view visibility
 *
 * @param product The product to add in the shopping cart
 * @param imgCart The array of images whose visibility will change
 */
fun addProductShoppingCart(product: Product, imgCart: Array<ImageView>?) {

    if (JWT?.id == null) {
        TOTAL_PRICE_SHOPPING_CART += product.price!!
        LIST_PRODUCT_SHOPPING_CART.add(product)
        if (imgCart != null) {
            showHide(imgCart)
        }
    } else {
        //initiate the service
        CoroutineScope(Dispatchers.IO).launch {
            val res =
                USE_API.addProductShoppingCart(
                    "Bearer " + JWT!!.accessToken,
                    JWT!!.id,
                    ShoppingCartItem(product, 1)
                )
            if (res?.code() == HTTP_OK) {
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
        }
    }
}

/**
 * Remove a product to the shopping cart and change the shopping card image view visibility
 *
 * @param product The product to remove in the shopping cart
 * @param imgCart The array of images whose visibility will change
 */
fun removeProductShoppingCart(product: Product, imgCart: Array<ImageView>?) {

    if (JWT?.id == null) {
        TOTAL_PRICE_SHOPPING_CART -= product.price!!
        LIST_PRODUCT_SHOPPING_CART.remove(product)
        if (imgCart != null) {
            showHide(imgCart)
        }
    } else {
        //initiate the service

        CoroutineScope(Dispatchers.IO).launch {
            val res = USE_API.removeProductShoppingCart(
                "Bearer " + JWT!!.accessToken,
                product.id!!,
                JWT!!.id
            )

            if (res?.code() == HTTP_OK) {
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
        }
    }
}

/**
 * Get all product available !
 *
 * @return the list of all product available
 */
fun removeAllProductFromShopping() {
    for (product in LIST_PRODUCT_SHOPPING_CART){
        removeProductShoppingCart(product,null)
        getShoppingCart()
    }
}
/**
 * Get all product available !
 *
 * @return the list of all product available
 */
fun getAllProducts(): LiveData<MutableList<Product>> {
    val liveProduct = MutableLiveData<MutableList<Product>>()

    CoroutineScope(Dispatchers.IO).launch {

        val res = USE_API.getProductList()

        if (res.code() == HTTP_OK) {
            LIST_ALL_PRODUCT = res?.body()!!.toMutableList()
            liveProduct.postValue(LIST_ALL_PRODUCT)
        }
    }
    return liveProduct
}
