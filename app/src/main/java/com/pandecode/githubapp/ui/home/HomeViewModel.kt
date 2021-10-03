package com.pandecode.githubapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandecode.data.domain.model.User
import com.pandecode.data.domain.usecase.GithubUseCase
import com.pandecode.data.source.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: GithubUseCase) : ViewModel() {

    private val _data = MutableLiveData<Resource<List<User>>>()
    var data: LiveData<Resource<List<User>>> = _data

    fun searchUser(username: String) {
        viewModelScope.launch {
            useCase.getSearchUser(username).collect {
                _data.postValue(it)
            }
        }
    }

}