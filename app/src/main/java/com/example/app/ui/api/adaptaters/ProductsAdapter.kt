package com.example.app.ui.api.adaptaters

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
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
import com.example.app.ui.api.models.listProductShoppingCart
import com.example.app.ui.pages.home.DetailActivity
import com.squareup.picasso.Picasso
import java.util.ArrayList


/**
 *
 */
class ProductsAdapter(listProduct: MutableList<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    var listProduct = listProduct
    var parentId: Int = 0

    @SuppressLint("LongLogTag")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_cell, parent, false)

        if (parent.id === R.id.recyclerViewBasket || parent.id === R.id.recyclerViewPopularArticle) {
            Log.d("parentId", parent.id.toString())
            Log.d("R.id.recyclerViewPopularArticle", R.id.recyclerViewPopularArticle.toString())

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
                listProductShoppingCart.add(product)
            }
            imgRedHeart.setOnClickListener {
                showHide(imgHeart)
                listProductShoppingCart.remove(product)
            }
            if (product in listProductShoppingCart) {
                showHide(imgHeart)

            }

            if (parentId === R.id.recyclerViewBasket)
                cardView.layoutParams.width = WRAP_CONTENT
            if (parentId === R.id.recyclerViewPopularArticle) {
                cardView.layoutParams.width = 500
            }

        }


        // Manage the toggle event on heart's article click
        fun showHide(imgViews: Array<ImageView>) {
            for (view in imgViews)
                view.visibility = if(view.visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE

        }

    }
}