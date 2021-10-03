package com.pandecode.data.domain.usecase

import com.pandecode.data.domain.model.DetailUser
import com.pandecode.data.domain.model.Repository
import com.pandecode.data.domain.model.SearchUserItem
import com.pandecode.data.source.Resource
import kotlinx.coroutines.flow.Flow

interface GithubUseCase {
    suspend fun getSearchUser(username: String): Flow<Resource<List<SearchUserItem>>>
    suspend fun getDetailUser(username: String): Flow<Resource<DetailUser>>
    suspend fun getFollower(username: String): Flow<Resource<List<SearchUserItem>>>
    suspend fun getFollowing(username: String): Flow<Resource<List<SearchUserItem>>>
    suspend fun getRepository(username: String): Flow<Resource<List<Repository>>>
}