package com.example.app.ui.pages.home


import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import app.R


/**
 * Barre de navigation !
 */
class HomeActivitySearch : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home_search)

        val listArticles = searchArticles()

        val searchView: SearchView = findViewById<SearchView>(R.id.search_bar)



        val articlesAdapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, listArticles)


        val listView: ListView = findViewById<ListView>(R.id.list_view)

        listView.adapter = articlesAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()

                if (listArticles.contains(query)) {
                    articlesAdapter.filter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                articlesAdapter.filter.filter(newText)
                return false
            }


        })
    }

    private fun searchArticles(): Array<String> {
        return arrayOf("Surat", "Mumbai", "Rajkot", "Ajay", "Prakesh")
    }

}

