package com.example.app.ui.data.viewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.databinding.CardCellBinding
import com.example.app.ui.data.interfaces.ArticleClickListener
import com.example.app.ui.data.models.Product
import com.example.app.ui.data.models.listArticleBasket

// Display manager of the card on the home page / fragment
class CardViewHolder(
    private val cardCellBinding: CardCellBinding,
    private val clickListener: ArticleClickListener
) : RecyclerView.ViewHolder(cardCellBinding.root) {

    // Allow to manipulate the current article that the user have clicked
    fun bindArticle(article: Product) {
        cardCellBinding.cover.setImageResource(article.cover)
        cardCellBinding.title.text = article.title
        cardCellBinding.author.text = article.author
        addOrRemoveArticleFromBasket(article)
        cardCellBinding.cardView.setOnClickListener {
            clickListener.onClick(article)
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