package com.pandecode.githubapp.ui.detail.fragment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandecode.data.domain.model.Repository
import com.pandecode.data.domain.usecase.GithubUseCase
import com.pandecode.data.source.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RepositoryViewModel(private val useCase: GithubUseCase) : ViewModel() {

    private val _data = MutableLiveData<Resource<List<Repository>>>()
    val data: LiveData<Resource<List<Repository>>> = _data

    fun getRepository(username: String) {
        viewModelScope.launch {
            useCase.getRepository(username).collect {
                _data.postValue(it)
            }
        }
    }

}