package com.example.app.ui.pages.home

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import app.R
import app.databinding.ActivityDetailBinding
import com.example.app.ui.LIST_ALL_PRODUCT_SORT_BY_CATEGORY
import com.example.app.ui.LIST_PRODUCT_FAVOURITE
import com.example.app.ui.PRODUCT_ID_EXTRA
import com.example.app.ui.adaptaters.ProductsAdapter
import com.example.app.ui.api.addProductShoppingCart
import com.example.app.ui.api.models.Product
import com.example.app.ui.api.removeProductShoppingCart
import com.example.app.ui.util.showHide
import com.example.app.ui.util.showImage
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
            if (product != null) {
                addProductShoppingCart(product, imgCart)
            }
        }
        imgShoppingCartFill.setOnClickListener {
            if (product != null) {
                removeProductShoppingCart(product, imgCart)
            }
        }

        imgRedHeart.visibility = View.INVISIBLE
        imgShoppingCartFill.visibility = View.INVISIBLE


        if (product != null) {
            showImage(imgHeart, imgCart, product)
        }


        binding.recyclerViewProductDetail.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ProductsAdapter(LIST_ALL_PRODUCT_SORT_BY_CATEGORY)
        }
    }
}