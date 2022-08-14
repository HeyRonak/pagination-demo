package com.example.paginationdemo.Actvities.Services


import com.example.paginationdemo.Actvities.Services.Models.PostResp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("posts")
    fun getPosts(@Query("_page") page: Int, @Query("_limit") per_page: Int): Call<PostResp>

}