package com.example.app.ui.OtherFragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import app.R
import com.example.app.ui.pages.settings.AddCreditCardActivity
import com.example.app.ui.util.notAvailableYet


/**
 * Fragment MeansOfPayment
 *
 * Propose the user to register several means of payment
 */
class MeansOfPayment : Fragment(R.layout.fragment_means_of_payment) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imgBitCoin = view.findViewById(R.id.imgBitCoin) as ImageView
        imgBitCoin.setOnClickListener { this.context?.let { it1 -> notAvailableYet(it1) } }

        val imgEthereum = view.findViewById(R.id.imgEthereum) as ImageView
        imgEthereum.setOnClickListener {
            this.context?.let { it1 -> notAvailableYet(it1) }

            val imgCreditCard = view.findViewById(R.id.imgCreditCard) as ImageView
            imgCreditCard.setOnClickListener {
                val intent = Intent(this.context, AddCreditCardActivity::class.java)
                startActivity(intent)
            }

            val imgPayPal = view.findViewById(R.id.imgPayPal) as ImageView
            imgPayPal.setOnClickListener {
                this.context?.let { it1 -> notAvailableYet(it1) }


            }


        }
    }
}