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
import androidx.recyclerview.widget.LinearLayoutManager
import app.R
import app.databinding.FragmentHomeBinding
import com.example.app.ui.data.DetailActivity
import com.example.app.ui.data.adaptaters.CardAdapter
import com.example.app.ui.data.interfaces.ArticleClickListener
import com.example.app.ui.data.models.ARTICLE_ID_EXTRA
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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment(), ArticleClickListener {

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
        populateArticles()

        val homeFragment = this
        binding.recyclerViewWhatIsUp.apply {
            layoutManager = GridLayoutManager(this@HomeFragment.context, 3)
            adapter = CardAdapter(articleList, homeFragment)
        }

        populatePopularArticles()

        binding.recyclerViewPopularArticle.apply {

            layoutManager =
                LinearLayoutManager(this@HomeFragment.context, LinearLayoutManager.HORIZONTAL, true)
            adapter = CardAdapter(articleList2, homeFragment)
        }
        executeCall()
        return root

    }

    override fun onClick(article: Product) {
        val intent = Intent(this@HomeFragment.context, DetailActivity::class.java)
        intent.putExtra(ARTICLE_ID_EXTRA, article.id)
        startActivity(intent)
    }

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

    private fun populateArticles() {
        val article1 = Product(
            R.drawable.ic_beige_chair,
            "Victoria Devine",
            "AGELESS BODY, TIMELESS MIND",
            "The definitive text on the healing powers of the mind/body connection. In Ageless Body, Timeless Mind, world-renowned pioneer of integrative medicine Deepak Chopra goes beyond ancient mind/body wisdom and current anti-ageing research to show that you do not have to grow old. With the passage of time, you can retain your physical vitality, creativity, memory and self-esteem. Based on the theories of Ayurveda and groundbreaking research, Chopra reveals how we can use our innate capacity for balance to direct the way our bodies metabolize time and achieve our unbounded potential."
        )
        articleList.add(article1)

        val article2 = Product(
            R.drawable.ic_blue_chair,
            "Amanda Lohrey",
            "THE MIRACLE OF MINDFULNESS",
            "This is the definitive article on mindfulness from the beloved Zen master and Nobel Peace Prize nominee Thich Nhat Hanh. With his signature clarity and warmth, he shares practical exercises and anecdotes to help us arrive at greater self-understanding and peacefulness, whether we are beginners or advanced students.\n" + "\n" + "Beautifully written, The Miracle of Mindfulness is the essential guide to welcoming presence in your life and truly living in the moment from the father of mindfulness.\n"
        )
        articleList.add(article2)

        val article3 = Product(
            R.drawable.ic_beige_chair,
            "M. Scott Peck",
            "THE ROAD LESS TRAVELLED",
            "A timeless classic in personal development, The Road Less Travelled is a landmark work that has inspired millions. Drawing on the experiences of his career as a psychiatrist, Scott Peck combines scientific and spiritual views to guide us through the difficult, painful times in life by showing us how to confront our problems through the key principles of discipline, love and grace.Teaching us how to distinguish dependency from love, how to become a more sensitive parent and how to connect with your true self, this incredible article is the key to accepting and overcoming life's challenges and achieving a higher level of self-understanding."
        )
        articleList.add(article3)

        val article4 = Product(
            R.drawable.ic_blue_chair,
            "Colleen Hoover",
            "IT ENDS WITH US",
            "'A brave and heartbreaking novel that digs its claws into you and doesn't let go, long after you've finished it' Anna Todd, author of the After series\n" + "\n" + "'A glorious and touching read, a forever keeper' USA Today\n" + "\n" + "'Will break your heart while filling you with hope' Sarah Pekkanen, Perfect Neighbors\n",
        )
        articleList.add(article4)

        val article5 = Product(
            R.drawable.ic_beige_chair,
            "Ross Coulthart",
            "IN PLAIN SIGHT",
            "Investigative journalist Ross Coulthart has been intrigued by UFOs since mysterious glowing lights were reported near New Zealand's Kaikoura mountains when he was a teenager. The 1978 sighting is just one of thousands since the 1940s, and yet research into UFOs is still seen as the realm of crackpots and conspiracy theorists."
        )
        articleList.add(article5)

        val article6 = Product(
            R.drawable.ic_blue_chair,
            "Richard Osman",
            "THE THURSDAY MURDER CLUB",
            "In a peaceful retirement village, four unlikely friends meet up once a week to investigate unsolved murders.\n" + "\n" + "But when a brutal killing takes place on their very doorstep, the Thursday Murder Club find themselves in the middle of their first live case.\n" + "\n" + "Elizabeth, Joyce, Ibrahim and Ron might be pushing eighty but they still have a few tricks up their sleeves.",
        )
        articleList.add(article6)

        val article7 = Product(
            R.drawable.ic_beige_chair,
            "Michael Robotham",
            "WHEN YOU ARE MINE",
            "Philomena McCarthy has defied the odds and become a promising young officer with the Metropolitan Police despite being the daughter of a notorious London gangster. Called to the scene of a domestic assault one day, she rescues a bloodied young woman, Tempe Brown, the mistress of a decorated detective. The incident is hushed up, but Phil has unwittingly made a dangerous enemy with powerful friends.\n"
        )
        articleList.add(article7)
    }

    private fun populatePopularArticles() {
        val article1 = Product(
            R.drawable.ic_beige_chair,
            "Victoria Devine",
            "AGELESS BODY, TIMELESS MIND",
            "The definitive text on the healing powers of the mind/body connection. In Ageless Body, Timeless Mind, world-renowned pioneer of integrative medicine Deepak Chopra goes beyond ancient mind/body wisdom and current anti-ageing research to show that you do not have to grow old. With the passage of time, you can retain your physical vitality, creativity, memory and self-esteem. Based on the theories of Ayurveda and groundbreaking research, Chopra reveals how we can use our innate capacity for balance to direct the way our bodies metabolize time and achieve our unbounded potential."
        )
        articleList2.add(article1)

        val article2 = Product(
            R.drawable.ic_blue_chair,
            "Amanda Lohrey",
            "THE MIRACLE OF MINDFULNESS",
            "This is the definitive article on mindfulness from the beloved Zen master and Nobel Peace Prize nominee Thich Nhat Hanh. With his signature clarity and warmth, he shares practical exercises and anecdotes to help us arrive at greater self-understanding and peacefulness, whether we are beginners or advanced students.\n" + "\n" + "Beautifully written, The Miracle of Mindfulness is the essential guide to welcoming presence in your life and truly living in the moment from the father of mindfulness.\n"
        )
        articleList2.add(article2)
        val article4 = Product(
            R.drawable.ic_blue_chair,
            "Colleen Hoover",
            "IT ENDS WITH US",
            "'A brave and heartbreaking novel that digs its claws into you and doesn't let go, long after you've finished it' Anna Todd, author of the After series\n" + "\n" + "'A glorious and touching read, a forever keeper' USA Today\n" + "\n" + "'Will break your heart while filling you with hope' Sarah Pekkanen, Perfect Neighbors\n",
        )
        articleList2.add(article4)

        val article5 = Product(
            R.drawable.ic_beige_chair,
            "Ross Coulthart",
            "IN PLAIN SIGHT",
            "Investigative journalist Ross Coulthart has been intrigued by UFOs since mysterious glowing lights were reported near New Zealand's Kaikoura mountains when he was a teenager. The 1978 sighting is just one of thousands since the 1940s, and yet research into UFOs is still seen as the realm of crackpots and conspiracy theorists."
        )
        articleList2.add(article5)

        val article6 = Product(
            R.drawable.ic_blue_chair,
            "Richard Osman",
            "THE THURSDAY MURDER CLUB",
            "In a peaceful retirement village, four unlikely friends meet up once a week to investigate unsolved murders.\n" + "\n" + "But when a brutal killing takes place on their very doorstep, the Thursday Murder Club find themselves in the middle of their first live case.\n" + "\n" + "Elizabeth, Joyce, Ibrahim and Ron might be pushing eighty but they still have a few tricks up their sleeves.",
        )
        articleList2.add(article6)

        val article7 = Product(
            R.drawable.ic_beige_chair,
            "Michael Robotham",
            "WHEN YOU ARE MINE",
            "Philomena McCarthy has defied the odds and become a promising young officer with the Metropolitan Police despite being the daughter of a notorious London gangster. Called to the scene of a domestic assault one day, she rescues a bloodied young woman, Tempe Brown, the mistress of a decorated detective. The incident is hushed up, but Phil has unwittingly made a dangerous enemy with powerful friends.\n"
        )
        articleList2.add(article7)
    }
}

