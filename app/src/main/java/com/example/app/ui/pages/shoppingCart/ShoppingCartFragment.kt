package com.example.app.ui.pages.shoppingCart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import app.R
import app.databinding.FragmentShoppingCartBinding
import com.example.app.ui.JWT
import com.example.app.ui.LIST_PRODUCT_SHOPPING_CART
import com.example.app.ui.adaptaters.ProductsAdapter
import com.example.app.ui.api.getShoppingCart
import com.example.app.ui.pages.authentication.SignInActivity

/**
 * Fragment ShoppingCart
 *
 * Show all product in the shopping cart
 */
class ShoppingCartFragment : Fragment() {
    // Link this activity to the view xml
    private var _binding: FragmentShoppingCartBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        val root: View = binding.root


        if (JWT?.id != null) {
            getShoppingCart()
            if (LIST_PRODUCT_SHOPPING_CART.size == 0) {
                Toast.makeText(context, "Le panier est vide", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnValidationShoppingCart.setOnClickListener {
            if (JWT?.id == null) {
                startActivity(Intent(context, SignInActivity::class.java));
            }
        }
        if (LIST_PRODUCT_SHOPPING_CART.size != 0) {

            binding.recyclerViewShoppingCart.apply {
                binding.btnValidationShoppingCart.setBackgroundColor(resources.getColor(R.color.navy_blue))
                binding.btnValidationShoppingCart.setOnClickListener {
                    if (JWT?.id == null) {
                        startActivity(Intent(context, SignInActivity::class.java));
                    }else {
                        startActivity(Intent(context, RecapShoppingCartActivity::class.java));
                    }
                }
                layoutManager = GridLayoutManager(context, 2)
                adapter = ProductsAdapter(LIST_PRODUCT_SHOPPING_CART)

            }
        } else {
            binding.btnValidationShoppingCart.visibility = View.GONE
            Toast.makeText(
                context,
                "Votre panier est vide... Mon roi !",
                Toast.LENGTH_SHORT
            ).show()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}