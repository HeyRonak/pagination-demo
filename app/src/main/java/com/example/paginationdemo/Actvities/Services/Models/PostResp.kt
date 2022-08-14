package com.example.paginationdemo.Actvities.Services.Models


import com.google.gson.annotations.SerializedName

class PostResp : ArrayList<PostResp.PostRespItem>(){
    data class PostRespItem(
        @SerializedName("body")
        val body: String="",
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("title")
        val title: String="",
        @SerializedName("userId")
        val userId: Int=0,

        var lastElement : Boolean = false

    )
}