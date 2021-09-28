package com.pandecode.data.domain.usecase

import com.pandecode.data.domain.model.SearchUserItem
import com.pandecode.data.source.Resource
import kotlinx.coroutines.flow.Flow

interface GithubUseCase {
    suspend fun searchUser(username: String) : Flow<Resource<List<SearchUserItem>>>
}