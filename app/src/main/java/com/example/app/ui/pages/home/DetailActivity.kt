package com.example.app.ui.pages.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import app.R
import app.databinding.ActivityDetailBinding
import com.example.app.ui.api.adaptaters.ProductsAdapter
import com.example.app.ui.api.models.*
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
            binding.btnOrder.text = " ${binding.btnOrder.text} ${product.price} €"


        }
        var imgBlackBorderHeart = findViewById<ImageView>(R.id.imgBlackBorderHeartDetail)
        var imgRedHeart = findViewById<ImageView>(R.id.imgRedHeartDetail)
        var imgShoppingCart = findViewById<ImageView>(R.id.imgShoppingCartDetail)
        var imgShoppingCartFill = findViewById<ImageView>(R.id.imgShoppingCartFillDetail)

        val imgHeart = arrayOf(imgBlackBorderHeart, imgRedHeart)

        imgBlackBorderHeart.setOnClickListener {
            showHide(imgHeart)
            if (product != null) {
                listProductFavourite.add(product)
            }
        }
        imgRedHeart.setOnClickListener {
            showHide(imgHeart)
            listProductFavourite.remove(product)
        }

        val imgCart: Array<ImageView> = arrayOf(imgShoppingCart, imgShoppingCartFill)
        imgShoppingCart.setOnClickListener {
            showHide(imgCart)
            if (product != null) {
                listProductShoppingCart.add(product)
            }
        }
        imgShoppingCartFill.setOnClickListener {
            showHide(imgCart)
            listProductShoppingCart.remove(product)

        }

        imgRedHeart.visibility = View.INVISIBLE
        imgShoppingCartFill.visibility = View.INVISIBLE


        if (product in listProductFavourite) {
            showHide(imgHeart)
        }
        if (product in listProductShoppingCart) {
            showHide(imgCart)
        }

        binding.recyclerViewProductDetail.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            for (p in productList) {
                if (p.id != product?.id) {
                     list.add(p)
                }
            }
            adapter = ProductsAdapter(list)
        }

    }

    // Manage the toggle event on heart's article click
    fun showHide(imgViews: Array<ImageView>) {
        for (view in imgViews)
            view.visibility =
                if (view.visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
    }
}