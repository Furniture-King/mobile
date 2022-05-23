package com.example.app.ui.pages.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.HorizontalScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import app.databinding.ActivityDetailBinding
import com.example.app.ui.api.adaptaters.ProductsAdapter
import com.example.app.ui.api.models.PRODUCT_ID_EXTRA
import com.example.app.ui.api.models.Product
import com.example.app.ui.api.models.productList
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product: Product? = intent.getSerializableExtra(PRODUCT_ID_EXTRA) as Product?
        if (product != null) {
            Picasso.get().load(product.srcImg).into(binding.imgProduct);
            binding.tvTitle.text = product.name
            binding.tvDescription.text = product.description
            binding.tvRatingbar.text = product.stars.toString() + "/5"
            binding.ratingBar.rating = product.stars!!
            binding.button.text = " ${binding.button.text} ${product.price} €"
        }

        binding.recyclerViewProductDetail.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)
            adapter = ProductsAdapter(productList)
        }
    }
}