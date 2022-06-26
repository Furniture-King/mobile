package com.example.app.ui.pages.search


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import app.databinding.ActivityResultSearchBinding
import com.example.app.ui.LIST_ALL_PRODUCT_SORT_BY_CATEGORY
import com.example.app.ui.adaptaters.ProductsAdapter


/**
 * Activity ResultSearchActivity
 *
 * Show to the user the result of the his search for the selected category
 */
class ResultSearchActivity : AppCompatActivity() {

    // Link this activity to the view xml
    private lateinit var binding: ActivityResultSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewSearchResult.apply {
            layoutManager =
                GridLayoutManager(applicationContext, 2)
            adapter = ProductsAdapter(LIST_ALL_PRODUCT_SORT_BY_CATEGORY)
        }
    }


}

