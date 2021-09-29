package com.pandecode.data.utils

import com.pandecode.data.domain.model.DetailUser
import com.pandecode.data.domain.model.SearchUserItem
import com.pandecode.data.source.remote.response.detail.DetailUserResponse
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
}