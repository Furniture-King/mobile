package com.example.app.ui.pages.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import app.databinding.ActivityDetailBinding
import com.example.app.ui.data.models.PRODUCT_ID_EXTRA
import com.example.app.ui.data.models.Product
import com.example.app.ui.data.models.productList

class DetailActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product: Product? = intent.getSerializableExtra(PRODUCT_ID_EXTRA) as Product?
//        val productID = intent.getStringExtra(PRODUCT_ID_EXTRA)
        Log.d("TEST", "product: ${product}")
//        val product = productID?.let { productFromID(it) }
        if(product != null)
        {
//            binding.imgProduct.setImageResource(product.imgProduct)
            binding.tvTitle.text = product.name
            binding.tvDescription.text = product.description
            val price ="${product.price} €"
            binding.tvPrice.text = price
        }

    }

    private fun productFromID(productID: String): Product?
    {
        for(product in productList)
        {
            if(product.id.toString() == productID) {
                return product
            }
        }
        return null
    }
}