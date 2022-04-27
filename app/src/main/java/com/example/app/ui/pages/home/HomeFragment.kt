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
import app.R
import app.databinding.FragmentHomeBinding
import com.example.app.ui.data.DetailActivity
import com.example.app.ui.data.ServiceBuilder
import com.example.app.ui.data.adaptaters.CardAdapter
import com.example.app.ui.data.adaptaters.ProductsAdapter
import com.example.app.ui.data.services.ProductService
import com.example.app.ui.data.models.PRODUCT_ID_EXTRA
import com.example.app.ui.data.models.Product
import com.example.app.ui.data.models.articleList
import com.example.app.ui.pages.ApiService
import com.example.app.ui.pages.authentication.SignInPage
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
    private val BASE_URL: String = "https://jsonplaceholder.typicode.com/"
    var articleList2 = mutableListOf<Product>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//            executeCall(textView)
//
//        }
        val tvConnexion: TextView = binding.tvConnection
        tvConnexion.setOnClickListener {
            val intent = Intent(this.context, SignInPage::class.java)
            startActivity(intent)
        }
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

//    override fun onClick(article: Product) {
//        val intent = Intent(this@HomeFragment.context, DetailActivity::class.java)
//        intent.putExtra(PRODUCT_ID_EXTRA, article.id)
//        startActivity(intent)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private val gson: Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }


    private fun executeCall() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = apiService.getUsers()

                if (response.isSuccessful && response.body() != null) {
                    val content = response.body()
                    Log.d(
                        "GET ALL PRODUCTS",
                        "\n\n res : \n" + content.toString()
                    )
                } else {
                    Toast.makeText(
                        this@HomeFragment.context,
                        "Error Occurred: ${response.message()}",
                        Toast.LENGTH_LONG
                    ).show()
                }

            } catch (e: Exception) {
                Toast.makeText(
                    this@HomeFragment.context,
                    "Error Occurred: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun populateProducts() {
        //initiate the service
        val destinationService = ServiceBuilder.buildService(ProductService::class.java)
        val requestCall = destinationService.getProductList()
        //make network call asynchronously
        requestCall.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful) {
                    val productList = response.body()!!
                    Log.d("Response", "product size : ${productList.size}")
                    val homeFragment = this

                    binding.recyclerViewWhatIsUp.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(this@HomeFragment.context, 3)
                        adapter = ProductsAdapter(response.body()!!)
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

