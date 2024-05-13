package com.msid.tweetsy.repository

import com.msid.tweetsy.api.TweetsApi
import com.msid.tweetsy.models.Tweet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository @Inject constructor(private val tweetsApi: TweetsApi) {


    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get()= _categories


    private val _tweets = MutableStateFlow<List<Tweet>>(emptyList())
    val tweets: StateFlow<List<Tweet>>
        get()= _tweets

    suspend fun getCategories() {

        val response = tweetsApi.getCategories()

        if (response.isSuccessful  && response.body()!=null){
            _categories.emit(response.body()!!)
        }
    }

    suspend fun getTweets(category: String) {

        val response = tweetsApi.getTweets("tweets[?(@.category==\"$category\")]")

        if (response.isSuccessful  && response.body()!=null){
            _tweets.emit(response.body()!!)
        }
    }
}