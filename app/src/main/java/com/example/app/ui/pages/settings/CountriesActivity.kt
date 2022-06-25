package com.example.app.ui.pages.settings

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter

import androidx.appcompat.app.AppCompatActivity
import app.databinding.ActivityPaysBinding
import com.example.app.ui.util.getAllCountry

/**
 * Activity Countries
 *
 * Show all the countries available to register a credit card
 */
class CountriesActivity : AppCompatActivity() {

    // Link this activity to the view xml
    private lateinit var binding: ActivityPaysBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaysBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // use arrayadapter and define an array
        val arrayAdapter: ArrayAdapter<*>
        val pays = getAllCountry()
        // access the listView from xml file
        arrayAdapter = ArrayAdapter(
            this,
            R.layout.simple_expandable_list_item_1, pays
        )
        binding.lvPays.adapter = arrayAdapter
        binding.lvPays.setOnItemClickListener { parent, view, position, id ->
            val element = arrayAdapter.getItem(position) // The item that was clicked
            startActivity(Intent(this, AddCreditCardActivity::class.java).putExtra("countrySelected", element))
            finish()
        }
    }


}