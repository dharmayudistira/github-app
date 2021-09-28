package com.pandecode.data.domain.usecase

import com.pandecode.data.domain.model.SearchUserItem
import com.pandecode.data.domain.repository.IGithubRepository
import com.pandecode.data.source.Resource
import kotlinx.coroutines.flow.Flow

class GithubInteractor(private val githubRepository: IGithubRepository) : GithubUseCase {
    override suspend fun searchUser(username: String): Flow<Resource<List<SearchUserItem>>> =
        githubRepository.searchUser(username)
}