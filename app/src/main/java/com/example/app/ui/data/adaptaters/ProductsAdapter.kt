package com.example.app.ui.data.adaptaters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.R
import com.example.app.ui.data.models.Product
import com.squareup.picasso.Picasso

class ProductsAdapter(private val countriesList: List<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_cell, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countriesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Response", "List Count :${countriesList.size} ")
        return holder.bind(countriesList[position])
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
            if (imgProduct!== null) {
                Picasso.get().load(R.drawable.ic_beige_chair).into(imgProduct);
            }
        }

    }
}