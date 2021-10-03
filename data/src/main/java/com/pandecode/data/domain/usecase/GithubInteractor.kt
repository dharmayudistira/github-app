package com.pandecode.data.domain.usecase

import com.pandecode.data.domain.model.User
import com.pandecode.data.domain.repository.IGithubRepository

class GithubInteractor(private val githubRepository: IGithubRepository) : GithubUseCase {
    override suspend fun getSearchUser(username: String) = githubRepository.getSearchUser(username)
    override suspend fun getDetailUser(username: String) = githubRepository.getDetailUser(username)
    override suspend fun getFollower(username: String) = githubRepository.getFollower(username)
    override suspend fun getFollowing(username: String) = githubRepository.getFollowing(username)
    override suspend fun getRepository(username: String) = githubRepository.getRepository(username)
    override fun getAllFavoriteUser() = githubRepository.getAllFavoriteUser()
    override suspend fun insertUser(user: User) = githubRepository.insertUser(user)
    override suspend fun deleteUser(user: User) = githubRepository.deleteUser(user)
    override fun getUserByUsername(username: String) =
        githubRepository.getUserByUsername(username)
}