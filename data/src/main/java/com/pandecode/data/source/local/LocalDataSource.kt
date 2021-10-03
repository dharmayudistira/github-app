package com.pandecode.data.source.local

import com.pandecode.data.source.local.database.GithubDao
import com.pandecode.data.source.local.database.UserEntity

class LocalDataSource(private val githubDao: GithubDao) {
    fun getAllFavoriteUser() = githubDao.getAllFavoriteUser()
    suspend fun insertUser(user: UserEntity) = githubDao.insertUser(user)
    suspend fun deleteUser(user: UserEntity) = githubDao.deleteUser(user)
    fun getUserByUsername(username: String) = githubDao.getUserByUsername(username)
}