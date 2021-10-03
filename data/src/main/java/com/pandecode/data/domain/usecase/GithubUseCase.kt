package com.pandecode.data.domain.usecase

import com.pandecode.data.domain.model.DetailUser
import com.pandecode.data.domain.model.Repository
import com.pandecode.data.domain.model.User
import com.pandecode.data.source.Resource
import kotlinx.coroutines.flow.Flow

interface GithubUseCase {
    suspend fun getSearchUser(username: String): Flow<Resource<List<User>>>
    suspend fun getDetailUser(username: String): Flow<Resource<DetailUser>>
    suspend fun getFollower(username: String): Flow<Resource<List<User>>>
    suspend fun getFollowing(username: String): Flow<Resource<List<User>>>
    suspend fun getRepository(username: String): Flow<Resource<List<Repository>>>
    fun getAllFavoriteUser(): Flow<List<User>>
    suspend fun insertUser(user: User)
    suspend fun deleteUser(user: User)
    fun getUserByUsername(username: String): Flow<List<User>>
}