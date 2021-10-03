package com.pandecode.data.utils

import com.pandecode.data.domain.model.DetailUser
import com.pandecode.data.domain.model.Repository
import com.pandecode.data.domain.model.SearchUserItem
import com.pandecode.data.source.remote.response.detail.DetailUserResponse
import com.pandecode.data.source.remote.response.repository.RepositoryResponse
import com.pandecode.data.source.remote.response.search.SearchUserItemResponse

object DataMapper {

    fun mapSearchResponseToDomain(input: List<SearchUserItemResponse>) =
        input.map {
            SearchUserItem(
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
}