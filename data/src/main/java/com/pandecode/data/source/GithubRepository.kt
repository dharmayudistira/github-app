package com.pandecode.data.source

import com.pandecode.data.domain.model.User
import com.pandecode.data.domain.repository.IGithubRepository
import com.pandecode.data.source.local.LocalDataSource
import com.pandecode.data.source.remote.RemoteDataSource
import com.pandecode.data.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GithubRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IGithubRepository {

    override suspend fun getSearchUser(username: String) = flow {
        emit(Resource.Loading)

        // Delaying for half a second to make sure that loading state is visible
        delay(500)

        try {
            val response = remoteDataSource.getSearchUser(username)
            val data = response.items

            if (data.isNotEmpty()) {
                val domain = DataMapper.mapUserResponseToDomain(data)
                emit(Resource.Success(domain))
            } else {
                emit(Resource.Empty)
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Something went wrong"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getDetailUser(username: String) = flow {
        emit(Resource.Loading)

        // Delaying for half a second to make sure that loading state is visible
        delay(500)

        try {
            val data = remoteDataSource.getDetailUser(username)
            val domain = DataMapper.mapDetailResponseToDomain(data)
            emit(Resource.Success(domain))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Something went wrong"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getFollower(username: String) = flow {
        emit(Resource.Loading)

        // Delaying for half a second to make sure that loading state is visible
        delay(500)

        try {
            val data = remoteDataSource.getFollower(username)

            if (data.isNotEmpty()) {
                val domain = DataMapper.mapUserResponseToDomain(data)
                emit(Resource.Success(domain))
            } else {
                emit(Resource.Empty)
            }

        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Something went wrong"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getFollowing(username: String) = flow {
        emit(Resource.Loading)

        // Delaying for half a second to make sure that loading state is visible
        delay(500)

        try {
            val data = remoteDataSource.getFollowing(username)

            if (data.isNotEmpty()) {
                val domain = DataMapper.mapUserResponseToDomain(data)
                emit(Resource.Success(domain))
            } else {
                emit(Resource.Empty)
            }

        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Something went wrong"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getRepository(username: String) = flow {
        emit(Resource.Loading)

        // Delaying for half a second to make sure that loading state is visible
        delay(500)

        try {
            val data = remoteDataSource.getRepository(username)

            if (data.isNotEmpty()) {
                val domain = DataMapper.mapRepositoryResponseToDomain(data)
                emit(Resource.Success(domain))
            } else {
                emit(Resource.Empty)
            }

        } catch (e: java.lang.Exception) {
            emit(Resource.Error(e.message ?: "Something went wrong"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getAllFavoriteUser() = flow {
        localDataSource.getAllFavoriteUser().collect {
            val domain = DataMapper.mapUserEntityToUserDomain(it)
            emit(domain)
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun insertUser(user: User) {
        val domain = DataMapper.mapUserDomainToEntity(user)
        localDataSource.insertUser(domain)
    }

    override suspend fun deleteUser(user: User) {
        val domain = DataMapper.mapUserDomainToEntity(user)
        localDataSource.deleteUser(domain)
    }

    override fun getUserByUsername(username: String) = flow {
        localDataSource.getUserByUsername(username).collect {
            val domain = DataMapper.mapUserEntityToUserDomain(it)
            emit(domain)
        }
    }.flowOn(Dispatchers.IO)
}