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
import com.example.app.ui.util.showHide
import com.squareup.picasso.Picasso

/**
 * Activity of product detail page
 *
 * Show more detail about the selected product
 */
class ProductDetailActivity : AppCompatActivity() {

    // Link this activity to the view xml
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product: Product? = intent.getSerializableExtra(PRODUCT_ID_EXTRA) as Product?
        if (product != null) {
            Picasso.get().load(product.srcImg[0]).into(binding.imgProduct);
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
                LIST_PRODUCT_FAVOURITE.add(product)
            }
        }
        imgRedHeart.setOnClickListener {
            showHide(imgHeart)
            LIST_PRODUCT_FAVOURITE.remove(product)
        }

        val imgCart: Array<ImageView> = arrayOf(imgShoppingCart, imgShoppingCartFill)
        imgShoppingCart.setOnClickListener {
            showHide(imgCart)
            if (product != null) {
                LIST_PRODUCT_SHOPPING_CART.add(product)
            }
        }
        imgShoppingCartFill.setOnClickListener {
            showHide(imgCart)
            LIST_PRODUCT_SHOPPING_CART.remove(product)
        }

        imgRedHeart.visibility = View.INVISIBLE
        imgShoppingCartFill.visibility = View.INVISIBLE


        if (product in LIST_PRODUCT_FAVOURITE) {
            showHide(imgHeart)
        }
        if (product in LIST_PRODUCT_SHOPPING_CART) {
            showHide(imgCart)
        }

        binding.recyclerViewProductDetail.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            var list : MutableList<Product> = mutableListOf()
            for (p in LIST_ALL_PRODUCT) {
                if (p.id != product?.id) {
                     list.add(p)
                }
            }
            adapter = ProductsAdapter(list)
        }
    }
}