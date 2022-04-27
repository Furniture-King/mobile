package com.example.app.ui.data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.databinding.ActivityDetailBinding
import com.example.app.ui.data.models.PRODUCT_ID_EXTRA
import com.example.app.ui.data.models.Product
import com.example.app.ui.data.models.articleList

class DetailActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        val productID = intent.getIntExtra(PRODUCT_ID_EXTRA, -1)
//        val product = articleFromID(productID)
//        if(product != null)
//        {
////            binding.imgProduct.setImageResource(product.imgProduct)
//            binding.tvTitle.text = product.name
//            binding.tvDescription.text = product.description
//            val price ="${product.price} e"
//            binding.tvPrice.text = price
//        }
    }

//    private fun articleFromID(articleID: Integer): Product?
//    {
//        for(article in articleList)
//        {
//            if(article.id == articleID)
//                return article
//        }
//        return null
//    }
}