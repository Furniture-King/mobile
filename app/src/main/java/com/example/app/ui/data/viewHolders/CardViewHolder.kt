package com.example.app.ui.data.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.databinding.CardCellBinding
import com.example.app.ui.data.models.Product
import com.example.app.ui.data.models.listArticleBasket
import com.example.app.ui.data.services.ProductService

// Display manager of the card on the home page / fragment
class CardViewHolder(
    private val cardCellBinding: CardCellBinding,
    private val productService: ProductService
) : RecyclerView.ViewHolder(cardCellBinding.root) {

    // Allow to manipulate the current article that the user have clicked
    fun bindArticle(product: Product) {
//        cardCellBinding.imgProduct.setImageResource(product.imgProduct)
        cardCellBinding.tvTitle.text = product.name
        cardCellBinding.tvDescription.text = product.description
        val price ="${product.price} e"
        cardCellBinding.tvPrice.text = price
        addOrRemoveArticleFromBasket(product)
        cardCellBinding.cardView.setOnClickListener {
            productService.onClick(product)
        }
    }

    // Manage the toggle event on heart's article click and put or remove article in the basket
    fun addOrRemoveArticleFromBasket(article: Product) {
        cardCellBinding.imgBlackBorderHeart.setOnClickListener {
            showHide(cardCellBinding.imgBlackBorderHeart)
            showHide(cardCellBinding.imgRedHeart)
            addArticleToBasket(article)
        }
        cardCellBinding.imgRedHeart.setOnClickListener {
            showHide(cardCellBinding.imgBlackBorderHeart)
            showHide(cardCellBinding.imgRedHeart)
            removeArticleToBasket(article)

        }
    }

    // Add an article from the basket
    private fun addArticleToBasket(article: Product) {
        listArticleBasket.add(article)
    }

    // Remove an article from the basket
    private fun removeArticleToBasket(article: Product) {
        listArticleBasket.remove(article)
    }

    // Manage the toggle event on heart's article click
    fun showHide(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
    }
}