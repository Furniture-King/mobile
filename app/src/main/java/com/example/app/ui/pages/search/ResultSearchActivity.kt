package com.example.app.ui.pages.search


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import app.databinding.ActivityResultSearchBinding
import com.example.app.ui.api.adaptaters.ProductsAdapter
import com.example.app.ui.api.models.CATEGORY_ID_EXTRA
import com.example.app.ui.api.models.Product
import com.example.app.ui.api.models.LIST_ALL_PRODUCT


/**
 * Activity ResultSearchActivity
 *
 * Show to the user the result of the his search for the selected category
 */
class ResultSearchActivity : AppCompatActivity() {



    var listValue = mutableListOf<Product>()
    // Link this activity to the view xml
    private lateinit var binding: ActivityResultSearchBinding

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.extras?.containsKey(CATEGORY_ID_EXTRA) == true) {

            val listProductSortByCategory = intent.extras!!.getString(CATEGORY_ID_EXTRA)
            getlistProductSortByCategory(listProductSortByCategory)

            Log.d("listProductSortByCategory", listProductSortByCategory.toString())
            Log.d("listValue", listValue.toString())

            binding.recyclerViewSearchResult.apply {
                layoutManager =
                    GridLayoutManager(applicationContext, 2)
                adapter = ProductsAdapter(listValue)
            }
        }


    }

    private fun getlistProductSortByCategory(category: String?) {
        LIST_ALL_PRODUCT.forEach { product ->
            if (product.categoryName == category) {
                listValue.add(product)
            }
        }

    }
}

