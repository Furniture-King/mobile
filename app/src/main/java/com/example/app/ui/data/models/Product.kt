package com.example.app.ui.data.models

var articleList = mutableListOf<Product>()
var listArticleBasket = mutableListOf<Product>()

val ARTICLE_ID_EXTRA = "articleExtra"

class Product(
    var cover: Int,
    var author: String,
    var title: String,
    var description: String,
    val id: Int? = articleList.size
)