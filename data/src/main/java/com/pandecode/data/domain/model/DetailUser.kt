package com.pandecode.data.domain.model

data class DetailUser(
    val login: String,
    val company: String?,
    val id: Int,
    val publicRepos: Int,
    val followers: Int,
    val avatarUrl: String,
    val following: Int,
    val name: String?,
    val location: String?
)
