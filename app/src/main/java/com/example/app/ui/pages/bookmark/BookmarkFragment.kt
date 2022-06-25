package com.example.app.ui.pages.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import app.databinding.FragmentBookmarkBinding
import com.example.app.ui.api.adaptaters.ProductsAdapter
import com.example.app.ui.api.models.LIST_PRODUCT_FAVOURITE

/**
 * Fragment Bookmark
 *
 * Show all the favourite user product
 */
class BookmarkFragment : Fragment() {
    // Link this activity to the view xml
    private var _binding: FragmentBookmarkBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerViewBookmark.apply {
            if (LIST_PRODUCT_FAVOURITE.size != 0) {
                layoutManager = GridLayoutManager(context, 2)
                adapter = ProductsAdapter(LIST_PRODUCT_FAVOURITE)
            }else {
                Toast.makeText(
                    context,
                    "Your favourite is empty my lord !",
                    Toast.LENGTH_SHORT
                ).show()
            }
            return root
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}