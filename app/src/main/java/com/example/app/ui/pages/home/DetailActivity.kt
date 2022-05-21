package com.example.app.ui.pages.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import app.databinding.ActivityDetailBinding
import com.example.app.ui.api.models.PRODUCT_ID_EXTRA
import com.example.app.ui.api.models.Product
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product: Product? = intent.getSerializableExtra(PRODUCT_ID_EXTRA) as Product?
        if(product != null)
        {
            Picasso.get().load(product.srcImg).into(binding.imgProduct);
            binding.tvTitle.text = product.name
            binding.tvDescription.text = product.description
            binding.tvRatingbar.text = product.stars.toString()+ "/5"
            binding.button.text = " ${binding.button.text} ${product.price} €"
        }

    }


}