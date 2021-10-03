package com.pandecode.githubapp.ui.detail.fragment.follower

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandecode.data.domain.model.User
import com.pandecode.data.domain.usecase.GithubUseCase
import com.pandecode.data.source.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FollowerViewModel(private val useCase: GithubUseCase) : ViewModel() {
    private val _dataFollower = MutableLiveData<Resource<List<User>>>()
    val dataFollower: LiveData<Resource<List<User>>> = _dataFollower

    fun getFollower(username: String) {
        viewModelScope.launch {
            useCase.getFollower(username).collect {
                _dataFollower.postValue(it)
            }
        }
    }
}