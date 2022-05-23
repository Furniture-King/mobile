package com.example.app.ui.api.adaptaters

import android.annotation.SuppressLint
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
import com.example.app.ui.api.models.PRODUCT_ID_EXTRA
import com.example.app.ui.api.models.Product
import com.example.app.ui.api.models.listProductFavourite
import com.example.app.ui.api.models.listProductShoppingCart
import com.example.app.ui.pages.home.DetailActivity
import com.squareup.picasso.Picasso


/**
 *
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
//            Log.d("parentId", parent.id.toString())
//            Log.d("R.id.recyclerViewPopularArticle", R.id.recyclerViewPopularArticle.toString())

            parentId = parent.id
        }

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        Log.d("Response", "List Count :${productList.size} ")
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(PRODUCT_ID_EXTRA, listProduct[position])
            holder.itemView.context.startActivity(intent)
        }

        return holder.bind(listProduct[position], parentId, listProduct)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var cardView = itemView.findViewById<CardView>(R.id.cardView)
        var imgProduct = itemView.findViewById<ImageView>(R.id.imgProduct)
        var imgBlackBorderHeart = itemView.findViewById<ImageView>(R.id.imgBlackBorderHeart)
        var imgRedHeart = itemView.findViewById<ImageView>(R.id.imgRedHeart)
        var imgShoppingCart = itemView.findViewById<ImageView>(R.id.imgCart)
        var imgShoppingCartFill = itemView.findViewById<ImageView>(R.id.imgCartFill)

        var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        var tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        var tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        var ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)
        var tvRatingBar = itemView.findViewById<TextView>(R.id.tvRatingbar)


        fun bind(product: Product, parentId: Int, listProduct: MutableList<Product>) {
            val price = "${product.price} €"
            tvTitle.text = product.name
            tvPrice.text = price
            tvDescription.text = product.description
            ratingBar.rating = product.stars!!
            tvRatingBar.text = product.stars.toString() + "/5"

            if (product.srcImg !== null) {
                Picasso.get().load(product.srcImg).into(imgProduct);
            }

            val imgHeart = arrayOf(imgBlackBorderHeart, imgRedHeart)
            imgBlackBorderHeart.setOnClickListener {
                showHide(imgHeart)
                listProductFavourite.add(product)
            }
            imgRedHeart.setOnClickListener {
                showHide(imgHeart)
                listProductFavourite.remove(product)
            }

            val imgCart = arrayOf(imgShoppingCart, imgShoppingCartFill)
            imgShoppingCart.setOnClickListener {
                showHide(imgCart)
                listProductShoppingCart.add(product)
            }
            imgShoppingCartFill.setOnClickListener {
                showHide(imgCart)
                listProductShoppingCart.remove(product)
            }


            if (product in listProductFavourite) {
                showHide(imgHeart)
            }
            if (product in listProductShoppingCart) {
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


        // Manage the toggle event on heart's article click
        fun showHide(imgViews: Array<ImageView>) {
            for (view in imgViews)
                view.visibility =
                    if (view.visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
        }

    }
}