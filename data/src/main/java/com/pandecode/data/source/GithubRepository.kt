package com.pandecode.data.source

import com.pandecode.data.domain.model.SearchUserItem
import com.pandecode.data.domain.repository.IGithubRepository
import com.pandecode.data.source.remote.RemoteDataSource
import com.pandecode.data.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class GithubRepository(private val remoteDataSource: RemoteDataSource) : IGithubRepository {
    override suspend fun searchUser(username: String) = flow {
        emit(Resource.Loading)

        try {
            val response = remoteDataSource.searchUser(username)
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
}