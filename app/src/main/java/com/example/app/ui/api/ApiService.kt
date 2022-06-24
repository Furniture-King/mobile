package com.example.app.ui.api

import com.example.app.ui.api.models.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {


    // Sign up
    @Headers("Content-Type: application/json")
    @POST("api/auth/sign-up")
    suspend fun signUp(@Body loginRequest: LoginRequest): Response<ResponseBody>

    // Sign in
    @Headers("Content-Type: application/json")
    @POST("api/auth/sign-in")
    suspend fun signIn(@Body loginRequest: LoginRequest): Response<JwtResponse>

    // Get all available products
    @GET("products")
    suspend fun getProductList(): Response<List<Product>>


    // Add a product in the shopping cart
    @Headers("Content-Type: application/json")
    @PUT("baskets/put/client/{clientId}")
    suspend fun addProductShoppingCart(
        @Header("authorization") authorization: String,
        @Path("clientId") clientId: String?,
        @Body shoppingCartItem: ShoppingCartItem
    ): Response<ResponseBody>


    // Remove a product in the shopping cart
    @Headers("Content-Type: application/json")
    @DELETE("baskets/delete/product/{produitId}/client/{clientId}")
    suspend fun removeProductShoppingCart(
        @Header("authorization") authorization: String?,
        @Path("produitId") produitId: String?,
        @Path("clientId") clientId: String?
    ): Response<ResponseBody>


    // Get all product in the shopping cart
    @Headers("Content-Type: application/json")
    @GET("basket/client/{clientId}")
    suspend fun getShoppingCart(
        @Header("authorization") authorization: String,
        @Path("clientId") clientId: String?
    ): Response<Basket>


    // Change the user profil setting
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


