package com.example.app.ui.pages.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.fragment.app.Fragment
import app.databinding.FragmentSearchBinding
import com.example.app.ui.LIST_ALL_PRODUCT
import com.example.app.ui.LIST_ALL_PRODUCT_SORT_BY_CATEGORY
import com.example.app.ui.api.getProductByCategory

/**
 * Fragment Search
 *
 * Allow a user to search products by category
 */
class SearchFragment : Fragment() {

    // Link this activity to the view xml
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    val listValue = arrayListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        loadCategories()

        val valueAdapter: ArrayAdapter<String> =
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_expandable_list_item_1,
                listValue!!
            )

        binding.listView.adapter = valueAdapter

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchBar.clearFocus()

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

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            getProductByCategory(valueAdapter.getItem(position).toString()).observe(viewLifecycleOwner) {
                LIST_ALL_PRODUCT_SORT_BY_CATEGORY = it
                startActivity(Intent(context, ResultSearchActivity::class.java))
            }
        }
        return root
    }



    private fun loadCategories() {
        LIST_ALL_PRODUCT.forEach { product ->
            if (!listValue.contains(product.categoryName.toString())) {
                listValue.add(product.categoryName.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}