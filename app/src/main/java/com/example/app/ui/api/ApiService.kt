package com.example.app.ui.api

import com.example.app.ui.api.models.*
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

/**
 * Interface of all API endpoints
 */
interface ApiService {

    /**
     * Sign up
     *
     * @param loginRequest object compose by email and password
     * @return a response to know if its ok or not about the request
     */
    @Headers("Content-Type: application/json")
    @POST("api/auth/sign-up")
    suspend fun signUp(@Body loginRequest: LoginRequest): Response<ResponseBody>

    /**
     * Sign in
     *
     * @param loginRequest object compose by email and password
     * @return information related to the user with its token
     */
    @Headers("Content-Type: application/json")
    @POST("api/auth/sign-in")
    suspend fun signIn(@Body loginRequest: LoginRequest): Response<JwtResponse>

    /**
     * Get all available products
     *
     * @return the product list available
     */
    @GET("products")
    suspend fun getProductList(): Response<List<Product>>


    /**
     * Add a product in the shopping cart
     *
     * @param authorization The token header
     * @param clientId The user id
     * @param shoppingCartItem The shopping cart
     * @return a response to know if its ok or not about the request
     */
    @Headers("Content-Type: application/json")
    @PUT("baskets/put/client/{clientId}")
    suspend fun addProductShoppingCart(
        @Header("authorization") authorization: String,
        @Path("clientId") clientId: String?,
        @Body shoppingCartItem: ShoppingCartItem
    ): Response<ResponseBody>

    /**
     * Remove a product in the shopping cart
     *
     * @param authorization The token header
     * @param produitId The product ID
     * @param clientId The user ID
     * @return a response to know if its ok or not about the request
     */

    @Headers("Content-Type: application/json")
    @DELETE("baskets/delete/product/{produitId}/client/{clientId}")
    suspend fun removeProductShoppingCart(
        @Header("authorization") authorization: String?,
        @Path("produitId") produitId: String?,
        @Path("clientId") clientId: String?
    ): Response<ResponseBody>


    /**
     * Get all product in the shopping cart
     *
     * @param authorization The token header
     * @param clientId The user ID
     * @return the shopping cart
     */
    @Headers("Content-Type: application/json")
    @GET("basket/client/{clientId}")
    suspend fun getShoppingCart(
        @Header("authorization") authorization: String,
        @Path("clientId") clientId: String?
    ): Response<Basket>


    /**
     * Get all product in the bookmark
     *
     * @param authorization The token header
     * @param clientId The user ID
     * @return the bookmark
     */
    @Headers("Content-Type: application/json")
    @GET("favs/client/{clientId}")
    suspend fun getBookmark(
        @Header("authorization") authorization: String?,
        @Path("clientId") clientId: String?
    ): Response<List<Product>>

    /**
     * Order all product in the shopping cart
     *
     * @param authorization The token header
     * @return a response to know if its ok or not about the request
     */
    @Headers("Content-Type: application/json")
    @POST("payment-intents")
    suspend fun orderProduct(
        @Header("authorization") authorization: String,
    ): Response<ResponseBody>

    /**
     * Order all product in the shopping cart
     *
     * @param authorization The token header
     * @return a response to know if its ok or not about the request
     */
    @Headers("Content-Type: application/json")
    @GET("products/category/{categoryName}")
    suspend fun getProductByCategory(
        @Path("categoryName") categoryName: String,
    ): Response<List<Product>>

    /**
     * Add a product in the shopping cart
     *
     * @param authorization The token header
     * @param clientId The user id
     * @param shoppingCartItem The shopping cart
     * @return a response to know if its ok or not about the request
     */
    @Headers("Content-Type: application/json")
    @PUT("favs/put/client/{clientId}")
    suspend fun addProductBookmark(
        @Header("authorization") authorization: String,
        @Path("clientId") clientId: String?,
        @Body product: Product
    ): Response<ResponseBody>

    /**
     * Remove a product in the shopping cart
     *
     * @param authorization The token header
     * @param produitId The product ID
     * @param clientId The user ID
     * @return a response to know if its ok or not about the request
     */

    @Headers("Content-Type: application/json")
    @DELETE("favs/delete/product/{produitId}/client/{clientId}")
    suspend fun removeProductBookmark(
        @Header("authorization") authorization: String?,
        @Path("produitId") produitId: String?,
        @Path("clientId") clientId: String?
    ): Response<ResponseBody>
}


