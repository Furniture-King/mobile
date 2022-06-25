package com.example.app.ui.api.adaptaters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import app.R
import com.example.app.ui.api.addProductShoppingCart
import com.example.app.ui.api.models.*
import com.example.app.ui.api.removeProductShoppingCart
import com.example.app.ui.pages.home.ProductDetailActivity
import com.example.app.ui.util.showHide
import com.squareup.picasso.Picasso


/**
 * Product adapter
 *
 * Allow to change dynamically the cart view
 */
class ProductsAdapter(listProduct: MutableList<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    var listProduct = listProduct
    var parentId: Int = 0
    val recyclerViews = arrayListOf(
        R.id.recyclerViewProductDetail,
        R.id.recyclerViewBookmark,
        R.id.recyclerViewShoppingCart,
        R.id.recyclerViewPopularArticle
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_cell, parent, false)

        if (parent.id in recyclerViews) {
            parentId = parent.id
        }

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ProductDetailActivity::class.java)
            intent.putExtra(PRODUCT_ID_EXTRA, listProduct[position])
            holder.itemView.context.startActivity(intent)
        }

        return holder.bind(listProduct[position], parentId)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // the CardView
        var cardView = itemView.findViewById<CardView>(R.id.cardView)

        // all the ImageView
        var imgProduct = itemView.findViewById<ImageView>(R.id.imgProduct)
        var imgBlackBorderHeart = itemView.findViewById<ImageView>(R.id.imgBlackBorderHeart)
        var imgRedHeart = itemView.findViewById<ImageView>(R.id.imgRedHeart)
        var imgShoppingCart = itemView.findViewById<ImageView>(R.id.imgCart)
        var imgShoppingCartFill = itemView.findViewById<ImageView>(R.id.imgCartFill)

        // all the TextView
        var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        var tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        var tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        var tvRatingBar = itemView.findViewById<TextView>(R.id.tvRatingbar)

        // the RatingBar
        var ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)

        fun bind(product: Product, parentId: Int) {
            val price = "${product.price} €"
            tvTitle.text = product.name
            tvPrice.text = price
            tvDescription.text = product.description
            ratingBar.rating = product.stars!!
            tvRatingBar.text = product.stars.toString() + "/5"

            if (product.srcImg !== null) {
                Picasso.get().load(product.srcImg[0]).into(imgProduct);
            }

            val imgHeart = arrayOf(imgBlackBorderHeart, imgRedHeart)
            imgBlackBorderHeart.setOnClickListener {
                showHide(imgHeart)
                LIST_PRODUCT_FAVOURITE.add(product)
            }
            imgRedHeart.setOnClickListener {
                showHide(imgHeart)
                LIST_PRODUCT_FAVOURITE.remove(product)
            }

            val imgCart = arrayOf(imgShoppingCart, imgShoppingCartFill)
            imgShoppingCart.setOnClickListener {
                addProductShoppingCart(product, imgCart)
            }
            imgShoppingCartFill.setOnClickListener {
                removeProductShoppingCart(product, imgCart)
            }

            if (product in LIST_PRODUCT_FAVOURITE) {
                showHide(imgHeart)
            }
            if (product in LIST_PRODUCT_SHOPPING_CART) {
                showHide(imgCart)
            }

            if (parentId === R.id.recyclerViewShoppingCart || parentId === R.id.recyclerViewBookmark)
                cardView.layoutParams.width = WRAP_CONTENT
            if (parentId === R.id.recyclerViewPopularArticle || parentId === R.id.recyclerViewProductDetail) {
                // cardView
                cardView.layoutParams.width = 350
                cardView.layoutParams.height = 500

                // heart images
                imgBlackBorderHeart.layoutParams.width = 30
                imgBlackBorderHeart.layoutParams.height = 30
                imgRedHeart.layoutParams.width = 30
                imgRedHeart.layoutParams.height = 30

                // shopping cart images
                imgShoppingCart.layoutParams.width = 30
                imgShoppingCart.layoutParams.height = 30
                imgShoppingCartFill.layoutParams.width = 30
                imgShoppingCartFill.layoutParams.height = 30
            }
        }
    }
}