package com.example.app.ui.util

import com.example.app.ui.api.models.Product

fun AddToList(list: MutableList<Product>, product: Product) {
    if (!(product in list)){
        list.add(product)
    }
}