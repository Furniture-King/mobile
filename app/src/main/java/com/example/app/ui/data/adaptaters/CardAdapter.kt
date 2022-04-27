package com.example.app.ui.data.adaptaters

import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.databinding.CardCellBinding
import com.example.app.ui.data.services.ProductService
import com.example.app.ui.data.models.Product
import com.example.app.ui.data.viewHolders.CardViewHolder

class CardAdapter (
    private val articles: List<Product>,
    private val service: ProductService
    )
    : RecyclerView.Adapter<CardViewHolder>(), ListAdapter {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder
    {
        val from = LayoutInflater.from(parent.context)
        val binding = CardCellBinding.inflate(from, parent, false)
        return CardViewHolder(binding, service)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int)
    {
        holder.bindArticle(articles[position])
    }

    override fun getItemCount(): Int = articles.size
    override fun registerDataSetObserver(p0: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun unregisterDataSetObserver(p0: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    override fun getViewTypeCount(): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun areAllItemsEnabled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEnabled(p0: Int): Boolean {
        TODO("Not yet implemented")
    }

}