package com.example.app.ui.util

import android.widget.ImageView
import com.example.app.ui.LIST_PRODUCT_FAVOURITE
import com.example.app.ui.LIST_PRODUCT_SHOPPING_CART
import com.example.app.ui.api.models.Product

/**
 * Show image content
 *
 * @param imgHeart list of heart image
 * @param imgCart list of cart image
 */
fun showImage(imgHeart: Array<ImageView>,imgCart: Array<ImageView>, product: Product) {
    if (product in LIST_PRODUCT_FAVOURITE) {
        showHide(imgHeart)
    }
    if (product in LIST_PRODUCT_SHOPPING_CART) {
        showHide(imgCart)
    }
}