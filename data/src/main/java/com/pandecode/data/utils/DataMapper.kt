package com.pandecode.data.utils

import com.pandecode.data.domain.model.SearchUserItem
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

}