package com.example.app.ui.pages.search


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.R
import com.example.app.ui.api.adaptaters.ProductsAdapter
import com.example.app.ui.api.models.CATEGORY_ID_EXTRA
import com.example.app.ui.api.models.Product
import com.example.app.ui.api.models.productList


/**
 * Barre de navigation !
 */
class ResultSearchCategoryActivity : AppCompatActivity(R.layout.activity_result_search) {

    var listValue = mutableListOf<Product>()

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.extras?.containsKey(CATEGORY_ID_EXTRA) == true) {

            val listProductSortByCategory = intent.extras!!.getString(CATEGORY_ID_EXTRA)
            getlistProductSortByCategory(listProductSortByCategory)

            Log.d("listProductSortByCategory", listProductSortByCategory.toString())
            Log.d("listValue", listValue.toString())

            val recyclerViewSearchResult = findViewById<RecyclerView>(R.id.recyclerViewSearchResult)
            recyclerViewSearchResult.apply {
                layoutManager =
                    GridLayoutManager(this@ResultSearchCategoryActivity.applicationContext, 2)
                adapter = ProductsAdapter(listValue)
            }
        }
    }

    private fun getlistProductSortByCategory(category: String?) {
        productList.forEach { product ->
            if (product.categoryName == category) {
                listValue.add(product)
            }
        }

    }
}

