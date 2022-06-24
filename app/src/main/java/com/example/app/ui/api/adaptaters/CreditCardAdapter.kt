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
import com.example.app.ui.api.models.*
import com.example.app.ui.pages.home.DetailActivity
import com.example.app.ui.pages.settings.AddCreditCardActivity
import com.squareup.picasso.Picasso


/**
 *
 */
class CreditCardAdapter(listCreditCard: MutableList<CreditCard>) :
    RecyclerView.Adapter<CreditCardAdapter.ViewHolder>() {

    var listCreditCard = listCreditCard

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.credit_card_cell, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCreditCard.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        Log.d("Response", "List Count :${productList.size} ")
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, AddCreditCardActivity::class.java)
            intent.putExtra(CREDIT_CARD_ID_EXTRA, listCreditCard[position])
            holder.itemView.context.startActivity(intent)
        }

        return holder.bind(listCreditCard[position], listCreditCard)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // TODO : adapter to view ! cardcredit

        var cardView = itemView.findViewById<CardView>(R.id.cardView)
        var tvcardNumber = itemView.findViewById<TextView>(R.id.tvCardNumber)

        fun bind(creditCard: CreditCard, listCreditCard: MutableList<CreditCard>) {
            tvcardNumber.text = creditCard.cardNumber
            cardView.layoutParams.width = WRAP_CONTENT

        }
    }

}