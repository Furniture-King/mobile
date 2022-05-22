package com.example.app.ui.pages.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import app.databinding.FragmentHomeBinding
import com.example.app.ui.api.ServiceBuilder
import com.example.app.ui.api.adaptaters.ProductsAdapter
import com.example.app.ui.api.models.Product
import com.example.app.ui.api.models.user
import com.example.app.ui.api.ApiService
import com.example.app.ui.api.models.productList
import com.example.app.ui.pages.authentication.SignInPage
import com.example.app.ui.pages.settings.SettingsFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

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

//        val imgSearchBarCategory: ImageView = binding.imgSearchBarCategory
//        imgSearchBarCategory.setOnClickListener {
//            findNavController().navigate(R.id.action_navigation_home_to_navigation_search)
//        }
        if (productList.isEmpty()) {
            populateProducts()
        } else {
            displayProducts()
        }
        return root

    }

    @SuppressLint("LongLogTag")
    private fun isLogged() {
        val tvConnexion: TextView = binding.tvConnection
//        Log.d("client", "${client}")
//        Log.d("client?.equals(null)", "${client?.equals(null)}")
//        Log.d("client == null", "${client?.equals(null) == null}")
        Log.d("(user?.id == null)",(user?.id == null).toString())
        Log.d("user?.id", ""+user?.id)
        Log.d("user",user.toString())
        if (user?.id == null) {
            tvConnexion.setOnClickListener {
                val intent = Intent(context, SignInPage::class.java)
                startActivity(intent)
            }
            tvConnexion.text = "Connexion"

        } else {
            tvConnexion.text = "Hi King !"
            if (user?.lastName != null) tvConnexion.text = "Hi, " + user?.lastName + "!"

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayProducts() {
        binding.recyclerViewWhatIsUp.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = ProductsAdapter(productList)
        }
        binding.recyclerViewPopularArticle.apply {

            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    true
                )
            adapter = ProductsAdapter(productList)
        }
    }

    private fun populateProducts() {
        //initiate the service
        val destinationService = ServiceBuilder.buildService(ApiService::class.java)
        val requestCall = destinationService.getProductList()
        //make network call asynchronously
        requestCall.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful) {
                    productList = response.body()!!.toMutableList()
                    Log.d("Response", "product size : ${productList.size}")

                    binding.recyclerViewWhatIsUp.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(context, 2)
                        adapter = ProductsAdapter(productList)

                    }
                    binding.recyclerViewPopularArticle.apply {

                        layoutManager =
                            LinearLayoutManager(
                                context,
                                LinearLayoutManager.HORIZONTAL,
                                true
                            )
                        adapter = ProductsAdapter(productList)
                    }
                } else {
                    Toast.makeText(
                       context,
                        "Something went wrong ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("Response", "Something went wrong : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Toast.makeText(
                    context,
                    "Something went wrong $t",
                    Toast.LENGTH_SHORT
                )
                    .show()
                Log.d("Response", "Something went wrong : $t")

            }
        })
    }


}

