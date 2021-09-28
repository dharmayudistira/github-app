package com.pandecode.data.source.remote.network

import com.pandecode.data.BuildConfig
import com.pandecode.data.source.remote.response.search.SearchUserResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GithubApiService {

    @GET("search/users")
    suspend fun getSearchUser(
        @Query("q") username: String
    ): SearchUserResponse

}