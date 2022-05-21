package com.example.app.ui.pages.search


import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import app.R


/**
 * Barre de navigation !
 */
class HomeActivitySearch : AppCompatActivity(R.layout.fragment_search) {

    lateinit var listValue: Array<out String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.extras?.containsKey("SEARCHGLOBAL") == true) {
            listValue = searchArticles()!!
        } else if (intent.extras?.containsKey("SEARCHCATEGORY") == true) {
            listValue = searchCategories()!!
        }
        val searchView: SearchView = findViewById<SearchView>(R.id.search_bar)


        val valueAdapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, listValue!!)


        val listView: ListView = findViewById<ListView>(R.id.list_view)

        listView.adapter = valueAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()

                if (listValue!!.contains(query)) {
                    valueAdapter.filter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                valueAdapter.filter.filter(newText)
                return false
            }
        })

        listView.setOnItemClickListener { parent, view, position, id ->
            val element = valueAdapter.getItem(position) // The item that was clicked
            val intentValue: Intent
            if (intent.extras?.containsKey("SEARCHGLOBAL") == true) {
                intentValue = Intent(this, ResultSearchGlobalActivity::class.java)
            } else if (intent.extras?.containsKey("SEARCHCATEGORY") == true) {
                intentValue = Intent(this, ResultSearchCategoryActivity::class.java)
            }
            intent.putExtra("countrySelected", element)
            startActivity(intent)
        }
    }

    private fun searchCategories(): Array<out String>? {
        return intent.getStringArrayExtra("SEARCHCATEGORY")
    }

    private fun searchArticles(): Array<out String>? {
        return intent.getStringArrayExtra("SEARCHGLOBAL")
    }

}

