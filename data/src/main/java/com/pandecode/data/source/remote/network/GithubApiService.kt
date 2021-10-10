package com.pandecode.data.source.remote.network

import com.pandecode.data.source.remote.response.detail.DetailUserResponse
import com.pandecode.data.source.remote.response.repository.RepositoryResponse
import com.pandecode.data.source.remote.response.search.SearchUserItemResponse
import com.pandecode.data.source.remote.response.search.SearchUserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    @GET("search/users")
    suspend fun getSearchUser(
        @Query("q") username: String
    ): SearchUserResponse

    @GET("users/{login}")
    suspend fun getDetailUser(
        @Path("login") login: String
    ): DetailUserResponse

    @GET("users/{login}/followers")
    suspend fun getFollower(
        @Path("login") login: String
    ): List<SearchUserItemResponse>

    @GET("users/{login}/following")
    suspend fun getFollowing(
        @Path("login") login: String
    ): List<SearchUserItemResponse>

    @GET("users/{login}/repos")
    suspend fun getRepository(
        @Path("login") login: String
    ): List<RepositoryResponse>

}