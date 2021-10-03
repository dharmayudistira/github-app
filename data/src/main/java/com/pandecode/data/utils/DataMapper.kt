package com.pandecode.data.utils

import com.pandecode.data.domain.model.DetailUser
import com.pandecode.data.domain.model.Repository
import com.pandecode.data.domain.model.User
import com.pandecode.data.source.local.database.UserEntity
import com.pandecode.data.source.remote.response.detail.DetailUserResponse
import com.pandecode.data.source.remote.response.repository.RepositoryResponse
import com.pandecode.data.source.remote.response.search.SearchUserItemResponse

object DataMapper {

    fun mapUserResponseToDomain(input: List<SearchUserItemResponse>) =
        input.map {
            User(
                id = it.id,
                avatarUrl = it.avatarUrl,
                login = it.login
            )
        }

    fun mapDetailResponseToDomain(input: DetailUserResponse) =
        DetailUser(
            login = input.login,
            company = input.company,
            id = input.id,
            publicRepos = input.publicRepos,
            followers = input.followers,
            avatarUrl = input.avatarUrl,
            following = input.following,
            name = input.name,
            location = input.location
        )

    fun mapRepositoryResponseToDomain(input: List<RepositoryResponse>) =
        input.map {
            Repository(
                stargazersCount = it.stargazersCount,
                language = it.language,
                id = it.id,
                fullName = it.fullName,
                name = it.name,
                description = it.description,
                forksCount = it.forksCount
            )
        }

    fun mapUserEntityToUserDomain(input: List<UserEntity>) =
        input.map {
            User(
                id = it.id,
                login = it.username,
                avatarUrl = it.avatarUrl
            )
        }

    fun mapUserDomainToEntity(input: User) = UserEntity(
        id = input.id,
        username = input.login,
        avatarUrl = input.avatarUrl
    )
}