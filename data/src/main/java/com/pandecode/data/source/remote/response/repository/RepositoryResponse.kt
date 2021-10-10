package com.pandecode.data.source.remote.response.repository

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(

    @field:SerializedName("stargazers_count")
    val stargazersCount: Int,

    @field:SerializedName("language")
    val language: String?,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("full_name")
    val fullName: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String?,

    @field:SerializedName("forks_count")
    val forksCount: Int
)