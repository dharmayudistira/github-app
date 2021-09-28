package com.pandecode.githubapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandecode.data.domain.model.SearchUserItem
import com.pandecode.data.domain.usecase.GithubUseCase
import com.pandecode.data.source.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: GithubUseCase) : ViewModel() {

    private val _data = MutableLiveData<Resource<List<SearchUserItem>>>()
    var data: LiveData<Resource<List<SearchUserItem>>> = _data

    fun searchUser(username: String) {
        viewModelScope.launch {
            useCase.searchUser(username).collect {
                _data.postValue(it)
            }
        }
    }

}