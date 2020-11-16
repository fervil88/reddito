package com.fernando.reddito.retrofit

import com.fernando.reddito.model.JSonObject
import retrofit2.http.GET
import retrofit2.Call


interface RetrofitAPI {

    /**
     * Functions for getting the posts
     */
    @GET("r/popular/top/.json")
    fun getPosts(): Call<JSonObject>


}