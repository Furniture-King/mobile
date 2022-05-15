package com.example.app.ui.pages.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.databinding.FragmentBasketBinding

class BasketFragment : Fragment() {
    private var _binding: FragmentBasketBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val settingViewModel =
            ViewModelProvider(this).get(BasketViewModel::class.java)
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val basketFragment = this
//        binding.recyclerViewBasket.apply {
//            layoutManager = GridLayoutManager(this@BasketFragment.context, 2)
//            adapter = CardAdapter(listArticleBasket, basketFragment)
//        }

        return root
    }

//    override fun onClick(article: Product) {
//        val intent = Intent(this@BasketFragment.context, DetailActivity::class.java)
//        intent.putExtra(ARTICLE_ID_EXTRA, article.id)
//        startActivity(intent)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}