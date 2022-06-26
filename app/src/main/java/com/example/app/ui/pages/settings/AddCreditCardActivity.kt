package com.example.app.ui.pages.settings

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.R
import app.databinding.ActivityAddCreditCardBinding
import com.example.app.ui.util.CreditCardNumberTextWatcher
import com.example.app.ui.util.ExpiryDateTextWatcher

/**
 * Activity AddCreditCard
 *
 * Allow to register a credit card
 */
class AddCreditCardActivity : AppCompatActivity(R.layout.activity_add_credit_card) {
    private lateinit var binding: ActivityAddCreditCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCreditCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edtCardNumber.addTextChangedListener(CreditCardNumberTextWatcher())
        binding.edtExpiryDate.addTextChangedListener(ExpiryDateTextWatcher(binding.edtExpiryDate))

        binding.tvPays.setOnClickListener {
            val intent = Intent(this.applicationContext, CountriesActivity::class.java)
            startActivity(intent)
        }

        var element = intent.getStringExtra("countrySelected")
        if (element != null) {
            binding.tvPays.text = element
        }

        binding.btnNext.setOnClickListener {

            finish()
        }
    }

}