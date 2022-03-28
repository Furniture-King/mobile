package com.example.app.ui.pages.home

import com.example.app.ui.data.Comment
import com.example.app.ui.data.Post
import com.example.app.ui.data.User
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<MutableList<User>>

    @GET("posts/{num}")
    suspend fun getPostById(@Path("num") num : Int): Response<Post>

    @GET("comments")
    suspend fun getCommentsByPost(@Query("postId") postId : Int): Response<MutableList<Comment>>

//
//    @GET("comments")
//    suspend fun getCommentsByPost(@Query("postId") postId : Int): Response<MutableList<>>

    @POST("posts")
    suspend fun createPost(@Body post: Post): Response<Post>
}


