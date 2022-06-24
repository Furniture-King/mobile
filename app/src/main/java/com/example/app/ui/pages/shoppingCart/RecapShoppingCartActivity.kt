package com.example.app.ui.pages.shoppingCart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import app.databinding.ActivityRecapShoppingCartBinding
import com.example.app.ui.api.adaptaters.ProductsRecapAdapter
import com.example.app.ui.api.models.LIST_PRODUCT_SHOPPING_CART
import com.example.app.ui.api.models.TOTAL_PRICE_SHOPPING_CART

class RecapShoppingCartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecapShoppingCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecapShoppingCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTotalPrice.text = "$TOTAL_PRICE_SHOPPING_CART €"
        binding.recyclerViewRecap.apply {
            layoutManager = GridLayoutManager(context, 1)
            adapter = ProductsRecapAdapter(LIST_PRODUCT_SHOPPING_CART, binding.root)
        }

        binding.btnBuy.setOnClickListener {
//            order()
            finish()
        }
    }


}