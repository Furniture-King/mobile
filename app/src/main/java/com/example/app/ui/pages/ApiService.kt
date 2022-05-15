package com.example.app.ui.pages

import com.example.app.ui.data.models.Client
import com.example.app.ui.data.models.Product
import com.example.app.ui.data.test.Comment
import com.example.app.ui.data.test.Post
import com.example.app.ui.data.test.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // CONNECTION
    @Headers("Content-Type: application/json")
    @POST("clients/sign-up")
    fun signUp(): Call<Client>

    @Headers("Content-Type: application/json")
    @POST("clients/sign-in")
    fun signIn(@Body client: Client): Call<Client>

    // GET ALL PRODUCTS
    @GET("products")
    fun getProductList(): Call<List<Product>>

    // GET ALL CLIENTS
    @GET("clients")
    fun getClientList(): Call<List<Client>>

    @GET("posts/{num}")
    suspend fun getPostById(@Path("num") num: Int): Response<Post>

    @GET("comments")
    suspend fun getCommentsByPost(@Query("postId") postId: Int): Response<MutableList<Comment>>

//
//    @GET("comments")
//    suspend fun getCommentsByPost(@Query("postId") postId : Int): Response<MutableList<>>

    @POST("posts")
    suspend fun createPost(@Body post: Post): Response<Post>
}


