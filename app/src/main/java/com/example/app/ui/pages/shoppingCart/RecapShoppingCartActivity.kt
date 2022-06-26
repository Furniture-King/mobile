package com.example.app.ui.pages.shoppingCart

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import app.databinding.ActivityRecapShoppingCartBinding
import com.example.app.ui.LIST_PRODUCT_SHOPPING_CART
import com.example.app.ui.MainActivity
import com.example.app.ui.TOTAL_PRICE_SHOPPING_CART
import com.example.app.ui.adaptaters.ProductsRecapAdapter
import com.example.app.ui.api.order
import com.example.app.ui.api.removeAllProductFromShopping
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult

/**
 * Recap shopping cart Page
 *
 * recap of all teh product in the shopping cart and allow to manage how many product the user wants to buy
 */
class RecapShoppingCartActivity : AppCompatActivity() {
    // Link this activity to the view xml
    private lateinit var binding: ActivityRecapShoppingCartBinding

    // Secret Client
    private lateinit var secretClient: String

    // The result of an payment attempt
    private lateinit var paymentSheet: PaymentSheet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecapShoppingCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        PaymentConfiguration.init(
            this.applicationContext,
            "pk_test_51LDpezKmKOU8NVzEiwFMhJUn4G6dzDs3gVdXxUll6RkIn6amHC4CFFQR9qQL4OUuzxC1vN46rhvxxOVRqAx1y84n00YLvYKrCQ"
        )
        binding.tvTotalPrice.text = "$TOTAL_PRICE_SHOPPING_CART €"
        binding.recyclerViewRecap.apply {
            layoutManager = GridLayoutManager(context, 1)
            adapter = ProductsRecapAdapter(LIST_PRODUCT_SHOPPING_CART, binding.root)
        }
        paymentSheet = PaymentSheet(this, ::onPaymentSheetResult)

        order().observe(this) {
            secretClient = it
        }
        binding.btnBuy.setOnClickListener {
            onPayClicked()
            startActivity(Intent(applicationContext, MainActivity::class.java));
            finish()
            removeAllProductFromShopping()

        }
    }

    /**
     * Enables payment actions to be initiated
     */
    private fun onPayClicked() {
        val configuration = PaymentSheet.Configuration(
            "FurnitureKing",
            googlePay = PaymentSheet.GooglePayConfiguration(
                currencyCode = "EUR",
                countryCode = "FR",
                environment = PaymentSheet.GooglePayConfiguration.Environment.Test
            )
        )
        // Present Payment Sheet
        paymentSheet.presentWithPaymentIntent(secretClient, configuration)
    }

    /**
     * Payment result manager
     *
     * @param paymentResult The result of an attempt to payment confirm
     */
    private fun onPaymentSheetResult(paymentResult: PaymentSheetResult) {
        when (paymentResult) {
            is PaymentSheetResult.Completed -> {
                Toast.makeText(applicationContext, "Payment is succesful !", Toast.LENGTH_SHORT)
                    .show()
            }
            is PaymentSheetResult.Canceled -> {
                Toast.makeText(applicationContext, "Payment Canceled", Toast.LENGTH_SHORT).show()
                finish()
            }
            is PaymentSheetResult.Failed -> {
                Toast.makeText(applicationContext, "Payment failed", Toast.LENGTH_SHORT).show()
                finish()
            }

        }
    }
}