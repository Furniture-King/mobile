package com.example.app.ui.api.adaptaters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.R
import com.example.app.ui.api.models.*
import com.example.app.ui.pages.home.DetailActivity
import com.squareup.picasso.Picasso


/**
 *
 */
class ProductsRecapAdapter(private val listProduct: MutableList<Product>, val view :View) :
    RecyclerView.Adapter<ProductsRecapAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val product_card_recap_cell = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_card_recap_cell, parent, false)
        return ViewHolder(product_card_recap_cell,view)
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(PRODUCT_ID_EXTRA, listProduct[position])
            holder.itemView.context.startActivity(intent)
        }

        return holder.bind(listProduct[position])
    }

    class ViewHolder(itemView: View, view: View) : RecyclerView.ViewHolder(itemView) {

        var imgProduct = itemView.findViewById<ImageView>(R.id.imgProduct)

        var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        var tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        var tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        var btnMinus = itemView.findViewById<TextView>(R.id.btnMinus)
        var btnPlus = itemView.findViewById<TextView>(R.id.btnPlus)
        var tvQte = itemView.findViewById<TextView>(R.id.tvQte)
        var tvTotalPrice = view.findViewById<TextView>(R.id.tvTotalPrice)

        fun bind(product: Product) {
            tvTitle.text = product.name
            tvDescription.text = product.description
            for (shoppingCartItem in SHOPPING_CART?.basketTab!! ){
                if (shoppingCartItem.product?.id == product.id) {
                    tvQte.text = shoppingCartItem?.qté.toString()!!
                    tvPrice.text = "${product.price!! * shoppingCartItem?.qté!!} €"
                }
            }
            if (product.srcImg !== null) {
                Picasso.get().load(product.srcImg[0]).into(imgProduct);
            }
            btnMinus.setOnClickListener {
                updatePrice(-1, product)
            }
            btnPlus.setOnClickListener {
                updatePrice(1, product)
            }
        }

        private fun updatePrice(qte: Int, product: Product) {
            var currentQte = tvQte.text.toString().toInt()
            var price = product.price
            if (currentQte + qte != 0) {
                for (shoppingCartItem in SHOPPING_CART?.basketTab!! ){
                    if (shoppingCartItem.product?.id == product.id) {
                        shoppingCartItem.qté = shoppingCartItem.qté?.plus(qte)

                        tvQte.text = (currentQte+qte).toString()
                        tvPrice.text = "${(currentQte+qte)*price!!} €"

                        TOTAL_PRICE_SHOPPING_CART += qte*price!!

                    }
                }
                // TOTAL ORDER PRICE
                tvTotalPrice.text ="${TOTAL_PRICE_SHOPPING_CART} €"
                SHOPPING_CART?.basketTotalPrice=TOTAL_PRICE_SHOPPING_CART
            }


        }
    }
}