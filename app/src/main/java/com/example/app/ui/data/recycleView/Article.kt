package com.example.app.ui.data.recycleView

var articleList = mutableListOf<Article>()

val ARTICLE_ID_EXTRA = "articleExtra"

class Article(
    var cover: Int,
    var author: String,
    var title: String,
    var description: String,
    val id: Int? = articleList.size
)