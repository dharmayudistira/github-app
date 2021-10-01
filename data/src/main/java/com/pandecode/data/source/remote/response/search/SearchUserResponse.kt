package com.pandecode.data.source.remote.response.search

import com.google.gson.annotations.SerializedName

data class SearchUserResponse(

    @field:SerializedName("items")
    val items: List<SearchUserItemResponse>
)

data class SearchUserItemResponse(

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("id")
    val id: Int

)
