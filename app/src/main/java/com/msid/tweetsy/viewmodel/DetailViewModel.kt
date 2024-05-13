package com.msid.tweetsy.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msid.tweetsy.repository.TweetRepository
import com.msid.tweetsy.models.Tweet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel  @Inject constructor(
    private val repository: TweetRepository,
    private val saveStateHandle: SavedStateHandle) : ViewModel() {

    val tweet : StateFlow<List<Tweet>>
        get() = repository.tweets

    init {

        viewModelScope.launch {
            val category = saveStateHandle.get<String>("category")?: "vision"
            repository.getTweets(category)
        }
    }
}