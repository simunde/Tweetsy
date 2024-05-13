package com.msid.tweetsy.api

import com.msid.tweetsy.models.Tweet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsApi {

    @GET("/v3/b/664154e2acd3cb34a846fc94?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String): Response<List<Tweet>>


    @GET("/v3/b/664154e2acd3cb34a846fc94?meta=false")
    @Headers("X-JSON-Path: tweets..category")
    suspend fun getCategories(): Response<List<String>>

}

