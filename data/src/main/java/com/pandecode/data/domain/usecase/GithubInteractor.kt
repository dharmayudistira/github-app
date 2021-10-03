package com.pandecode.data.domain.usecase

import com.pandecode.data.domain.model.Repository
import com.pandecode.data.domain.repository.IGithubRepository
import com.pandecode.data.source.Resource
import kotlinx.coroutines.flow.Flow

class GithubInteractor(private val githubRepository: IGithubRepository) : GithubUseCase {
    override suspend fun getSearchUser(username: String) = githubRepository.getSearchUser(username)
    override suspend fun getDetailUser(username: String) = githubRepository.getDetailUser(username)
    override suspend fun getFollower(username: String) = githubRepository.getFollower(username)
    override suspend fun getFollowing(username: String) = githubRepository.getFollowing(username)
    override suspend fun getRepository(username: String) = githubRepository.getRepository(username)
}