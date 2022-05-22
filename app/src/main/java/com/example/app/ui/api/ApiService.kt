package com.example.app.ui.api

import com.example.app.ui.api.models.Client
import com.example.app.ui.api.models.Product
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface ApiService {


    // INSCRIPTION
    @Headers("Content-Type: application/json")
    @POST("clients/sign-up")
    fun signUp(@Body client: Client): Call<Client>

    // CONNEXION
    @Headers("Content-Type: application/json")
    @POST("clients/sign-in")
    fun signIn(@Body client: Client): Call<Client>

    // OBTENIR TOUS LES PRODUITS
    @GET("products")
    fun getProductList(): Call<List<Product>>

    // OBTENIR TOUS LES CLIENTS
    @GET("clients")
    fun getClientList(): Call<List<Client>>

    // MODIFIER UN CLIENT
    @PUT("clients/put/{clientId}")
    fun updateProfile(@Path("clientId") @Body user: Client?): Call<Client>

//    @GET("posts/{num}")
//    suspend fun getPostById(@Path("num") num: Int): Response<Post>
//
//    @GET("comments")
//    suspend fun getCommentsByPost(@Query("postId") postId: Int): Response<MutableList<Comment>>
//
////
////    @GET("comments")
////    suspend fun getCommentsByPost(@Query("postId") postId : Int): Response<MutableList<>>
//
//    @POST("posts")
//    suspend fun createPost(@Body post: Post): Response<Post>
}


