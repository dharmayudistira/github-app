package com.pandecode.githubapp.ui.detail.fragment.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandecode.data.domain.model.SearchUserItem
import com.pandecode.data.domain.usecase.GithubUseCase
import com.pandecode.data.source.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FollowingViewModel(private val useCase: GithubUseCase) : ViewModel() {

    private val _dataFollowing = MutableLiveData<Resource<List<SearchUserItem>>>()
    val dataFollowing : LiveData<Resource<List<SearchUserItem>>> = _dataFollowing

    fun getFollowing(username: String) {
        viewModelScope.launch {
            useCase.getFollowing(username).collect {
                _dataFollowing.postValue(it)
            }
        }
    }

}