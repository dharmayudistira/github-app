package com.pandecode.data.source

import com.pandecode.data.domain.repository.IGithubRepository
import com.pandecode.data.source.remote.RemoteDataSource
import com.pandecode.data.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class GithubRepository(private val remoteDataSource: RemoteDataSource) : IGithubRepository {
    override suspend fun getSearchUser(username: String) = flow {
        emit(Resource.Loading)

        try {
            val response = remoteDataSource.getSearchUser(username)
            val data = response.items

            if (data.isNotEmpty()) {
                val domain = DataMapper.mapSearchResponseToDomain(data)
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

        try {
            val data = remoteDataSource.getDetailUser(username)
            val domain = DataMapper.mapDetailResponseToDomain(data)
            emit(Resource.Success(domain))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Something went wrong"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getFollower(username: String) =
        flow {
            emit(Resource.Loading)

            try {
                val data = remoteDataSource.getFollower(username)

                if (data.isNotEmpty()) {
                    val domain = DataMapper.mapSearchResponseToDomain(data)
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

        try {
            val data = remoteDataSource.getFollowing(username)

            if (data.isNotEmpty()) {
                val domain = DataMapper.mapSearchResponseToDomain(data)
                emit(Resource.Success(domain))
            } else {
                emit(Resource.Empty)
            }

        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Something went wrong"))
        }
    }.flowOn(Dispatchers.IO)
}