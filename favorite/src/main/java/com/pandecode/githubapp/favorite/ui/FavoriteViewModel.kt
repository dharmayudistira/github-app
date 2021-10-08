package com.pandecode.githubapp.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.pandecode.data.domain.usecase.GithubUseCase

class FavoriteViewModel(useCase: GithubUseCase) : ViewModel() {

    val data = useCase.getAllFavoriteUser().asLiveData()

}