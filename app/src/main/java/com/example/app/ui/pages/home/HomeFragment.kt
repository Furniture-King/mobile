package com.example.app.ui.pages.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import app.databinding.FragmentHomeBinding
import com.example.app.ui.data.ServiceBuilder
import com.example.app.ui.data.adaptaters.ProductsAdapter
import com.example.app.ui.data.services.ProductService
import com.example.app.ui.data.models.Product
import com.example.app.ui.data.models.client
import com.example.app.ui.data.models.productList
import com.example.app.ui.pages.ApiService
import com.example.app.ui.pages.authentication.SignInPage
import com.example.app.ui.pages.settings.SettingsFragment
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//            executeCall(textView)
//
//        }

        isLogged()

        val imageView: ImageView = binding.imgSearchBarGlobal
        imageView.setOnClickListener {
            val intent = Intent(this.context, HomeActivitySearch::class.java)
            startActivity(intent)

        }
//        populatePopularArticles()
//
//        binding.recyclerViewPopularArticle.apply {
//
//            layoutManager =
//                LinearLayoutManager(this@HomeFragment.context, LinearLayoutManager.HORIZONTAL, true)
//            adapter = CardAdapter(articleList2, homeFragment)
//        }
        populateProducts()

//        val homeFragment = this
//        binding.recyclerViewWhatIsUp.apply {
//            layoutManager = GridLayoutManager(this@HomeFragment.context, 3)
//            adapter = CardAdapter(articleList, homeFragment)
//        }
//        val homeFragment = this
//        binding.recyclerViewWhatIsUp.apply {
//            layoutManager = GridLayoutManager(this@HomeFragment.context, 3)
//            adapter = CardAdapter(articleList, homeFragment)
//        }


//        executeCall()
        return root

    }

    private fun isLogged() {
        val tvConnexion: TextView = binding.tvConnection
//        Log.d("client", "${client}")
//        Log.d("client?.equals(null)", "${client?.equals(null)}")
//        Log.d("client == null", "${client?.equals(null) == null}")

        if (client?.equals(null) == null) {
            tvConnexion.setOnClickListener {
                val intent = Intent(this.context, SignInPage::class.java)
                startActivity(intent)
            }
        } else {
            tvConnexion.text = "Hi, " + client?.lastName + "!"
            tvConnexion.setOnClickListener {
                val intent = Intent(this.context, SettingsFragment::class.java)
                startActivity(intent)
            }
        }
    }

//    override fun onClick(article: Product) {
//        val intent = Intent(this@HomeFragment.context, DetailActivity::class.java)
//        intent.putExtra(PRODUCT_ID_EXTRA, article.id)
//        startActivity(intent)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
                    val homeFragment = this

                    binding.recyclerViewWhatIsUp.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(this@HomeFragment.context, 3)
                        adapter = ProductsAdapter()
                    }
                } else {
                    Toast.makeText(
                        this@HomeFragment.context,
                        "Something went wrong ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("Response", "Something went wrong : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Toast.makeText(
                    this@HomeFragment.context,
                    "Something went wrong $t",
                    Toast.LENGTH_SHORT
                )
                    .show()
                Log.d("Response", "Something went wrong : $t")

            }
        })
    }


}

