package com.example.app.ui.data.recycleView

import androidx.recyclerview.widget.RecyclerView
import app.databinding.CardCellBinding

class CardViewHolder(
    private val cardCellBinding: CardCellBinding,
    private val clickListener: ArticleClickListener
) : RecyclerView.ViewHolder(cardCellBinding.root)
{
    fun bindArticle(article: Article)
    {
        cardCellBinding.cover.setImageResource(article.cover)
        cardCellBinding.title.text = article.title
        cardCellBinding.author.text = article.author

        cardCellBinding.cardView.setOnClickListener{
            clickListener.onClick(article)
        }
    }
}