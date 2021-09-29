package com.pandecode.data.source.remote

import com.pandecode.data.source.remote.network.GithubApiService
import com.pandecode.data.source.remote.response.search.SearchUserResponse
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.util.concurrent.Flow

class RemoteDataSource(private val githubApiService: GithubApiService) {
    suspend fun getSearchUser(username: String) = githubApiService.getSearchUser(username)
    suspend fun getDetailUser(username: String) = githubApiService.getDetailUser(username)
    suspend fun getFollower(username: String) = githubApiService.getFollower(username)
    suspend fun getFollowing(username: String) = githubApiService.getFollowing(username)
}