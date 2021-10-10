package com.pandecode.githubapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandecode.data.domain.model.DetailUser
import com.pandecode.data.domain.model.User
import com.pandecode.data.domain.usecase.GithubUseCase
import com.pandecode.data.source.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(private val useCase: GithubUseCase) : ViewModel() {

    private val _data = MutableLiveData<Resource<DetailUser>>()
    val data: LiveData<Resource<DetailUser>> = _data

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun getDetailUser(username: String) {
        viewModelScope.launch {
            useCase.getDetailUser(username).collect {
                _data.postValue(it)
            }
        }
    }

    fun checkFavorite(username: String) {
        viewModelScope.launch {
            try {
                useCase.getUserByUsername(username).collect {
                    if (it.isNotEmpty()) {
                        _isFavorite.postValue(true)
                    } else {
                        _isFavorite.postValue(false)
                    }
                }
            } catch (e: Exception) {
                _isFavorite.postValue(false)
            }
        }
    }

    fun insertToDatabase(data: DetailUser) {
        val user = User(
            id = data.id,
            avatarUrl = data.avatarUrl,
            login = data.login
        )

        viewModelScope.launch {
            useCase.insertUser(user)
        }
    }

    fun deleteFromDatabase(data: DetailUser) {
        val user = User(
            id = data.id,
            avatarUrl = data.avatarUrl,
            login = data.login
        )

        viewModelScope.launch {
            useCase.deleteUser(user)
        }
    }

}