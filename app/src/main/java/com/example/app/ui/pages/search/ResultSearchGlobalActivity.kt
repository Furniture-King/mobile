package com.example.app.ui.pages.search


import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import app.R


/**
 * Barre de navigation !
 */
class ResultSearchGlobalActivity : AppCompatActivity(R.layout.activity_result_search) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val searchView: SearchView = findViewById<SearchView>(R.id.search_bar)

    }

}

