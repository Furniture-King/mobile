package com.example.app.ui.util

import com.example.app.ui.api.models.Product

/**
 * Add a element Product in a list
 * check if its element already exist in the list otherwise it add it
 *
 * @param list list of product
 * @param product the product
 */
fun AddToList(list: MutableList<Product>, product: Product) {
    if (!(product in list)){
        list.add(product)
    }
}