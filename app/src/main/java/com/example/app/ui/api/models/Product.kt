package com.example.app.ui.api.models

import java.io.Serializable

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
