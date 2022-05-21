package com.example.app.ui.pages.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import app.databinding.FragmentBasketBinding
import com.example.app.ui.api.adaptaters.ProductsAdapter
import com.example.app.ui.api.models.listProductShoppingCart

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

        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerViewBasket.apply {
            layoutManager = GridLayoutManager(this@BasketFragment.context,2)
            adapter=ProductsAdapter(listProductShoppingCart)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}