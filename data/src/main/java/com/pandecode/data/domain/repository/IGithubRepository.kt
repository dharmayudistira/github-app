package com.pandecode.data.domain.repository

import com.pandecode.data.domain.model.SearchUserItem
import com.pandecode.data.source.Resource
import kotlinx.coroutines.flow.Flow

interface IGithubRepository {
    suspend fun searchUser(username: String) : Flow<Resource<List<SearchUserItem>>>
}