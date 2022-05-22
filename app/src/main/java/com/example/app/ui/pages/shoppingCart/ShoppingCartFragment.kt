package com.example.app.ui.pages.shoppingCart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import app.databinding.FragmentShoppingCartBinding
import com.example.app.ui.api.adaptaters.ProductsAdapter
import com.example.app.ui.api.models.listProductShoppingCart

class ShoppingCartFragment : Fragment() {
    private var _binding: FragmentShoppingCartBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerViewShoppingCart.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter=ProductsAdapter(listProductShoppingCart)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}