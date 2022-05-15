package com.example.app.ui.data.adaptaters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import app.R
import com.example.app.ui.data.models.PRODUCT_ID_EXTRA
import com.example.app.ui.data.models.Product
import com.squareup.picasso.Picasso
import com.example.app.ui.data.models.productList
import com.example.app.ui.pages.home.DetailActivity

class ProductsAdapter() :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_cell, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        Log.d("Response", "List Count :${productList.size} ")
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(PRODUCT_ID_EXTRA,productList[position])
            holder.itemView.context.startActivity(intent)
        }
        return holder.bind(productList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imgProduct = itemView.findViewById<ImageView>(R.id.imgProduct)
        var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        var tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        var tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        var ratingBar = itemView.findViewById<RatingBar>(R.id.ratingBar)

        fun bind(product: Product) {
            val price = "${product.price} €"
            tvTitle.text = product.name
            tvPrice.text = price
            tvDescription.text = product.description
            ratingBar.rating = product.stars
            if (imgProduct !== null) {
                Picasso.get().load(R.drawable.ic_beige_chair).into(imgProduct);
            }
        }


    }
}