package com.pandecode.githubapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandecode.data.domain.model.DetailUser
import com.pandecode.data.domain.model.SearchUserItem
import com.pandecode.data.domain.usecase.GithubUseCase
import com.pandecode.data.source.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(private val useCase: GithubUseCase) : ViewModel() {

    private val _data = MutableLiveData<Resource<DetailUser>>()
    val data : LiveData<Resource<DetailUser>> = _data



    fun getDetailUser(username: String) {
        viewModelScope.launch {
            useCase.getDetailUser(username).collect {
                _data.postValue(it)
            }
        }
    }

}