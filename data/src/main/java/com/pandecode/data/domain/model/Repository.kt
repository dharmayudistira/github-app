package com.pandecode.data.domain.model

data class Repository(
    val stargazersCount: Int,
    val language: String?,
    val id: Int,
    val fullName: String,
    val name: String,
    val description: String?,
    val forksCount: Int
)
