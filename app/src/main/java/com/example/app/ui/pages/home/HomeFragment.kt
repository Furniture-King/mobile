package com.example.app.ui.pages.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import app.databinding.FragmentHomeBinding
import com.example.app.ui.api.adaptaters.ProductsAdapter
import com.example.app.ui.api.getShoppingCart
import com.example.app.ui.api.models.*
import com.example.app.ui.api.getAllProducts
import com.example.app.ui.pages.authentication.SignInActivity

/**
 * Fragment Home page
 *
 * Show all product available and all popular product
 */
class HomeFragment : Fragment() {

    // Link this fragment to the view xml
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        isLogged()

        getAllProducts().observe(viewLifecycleOwner) {
            binding.recyclerViewWhatIsUp.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(context, 2)
                adapter = ProductsAdapter(it)

            }
            binding.recyclerViewPopularArticle.apply {
                layoutManager =
                    LinearLayoutManager(
                        context,
                        LinearLayoutManager.HORIZONTAL,
                        true
                    )
                adapter = ProductsAdapter(LIST_ALL_PRODUCT)
            }
        }

        if (LIST_ALL_PRODUCT.isEmpty()) {
            binding.whatIsUp.visibility = View.GONE
            binding.popularArticle.visibility = View.GONE
        }
        return root

    }

    /**
     * Check if the user is already log in the app
     */
    private fun isLogged() {
        val tvConnexion: TextView = binding.tvConnection

        if (JWT?.id == null) {
            tvConnexion.setOnClickListener {
                val intent = Intent(context, SignInActivity::class.java)
                startActivity(intent)
            }
            tvConnexion.text = "Connexion"

        } else {
            tvConnexion.text = "Hi King !"
            if (JWT?.username != null) tvConnexion.text =
                "Hi, " + JWT?.username + "!"
            getShoppingCart()
        }

        Log.d("user?.id", "" + user?.id)
        Log.d("user", user.toString())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

