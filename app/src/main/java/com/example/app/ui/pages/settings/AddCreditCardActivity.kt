package com.example.app.ui.pages.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import app.R
import app.databinding.ActivityAddCreditCardBinding

class AddCreditCardActivity : AppCompatActivity(R.layout.activity_add_credit_card) {
    private lateinit var binding: ActivityAddCreditCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCreditCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edtCardNumber.addTextChangedListener(object : TextWatcher {
            private var current = ""
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {            }
            override fun afterTextChanged(s: Editable) {
                if (s.toString() != current) {
                    val userInput =
                        s.toString().replace(nonDigits, "")
                    if (userInput.length <= 16) {
                        current = userInput.chunked(4).joinToString(" ")
                        s.filters = arrayOfNulls<InputFilter>(0)
                    }
                    s.replace(0, s.length, current, 0, current.length)
                }
            }
            private val nonDigits = Regex("[^\\d]")
        })

        binding.edtExpiryDate.addTextChangedListener(object : TextWatcher {
            private var current = ""
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {            }
            override fun afterTextChanged(s: Editable) {

            }
            private val nonDigits = Regex("[^\\d]")
        })
    }

}