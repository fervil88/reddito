package com.fernando.reddito.retrofit

import android.util.Log
import com.fernando.reddito.model.Child
import com.fernando.reddito.model.JSonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

class RetrofitController {

    private var service: RetrofitAPI? = null
    var mPostList: MutableList<Child> = mutableListOf()

    private fun getRService(): RetrofitAPI? {
        /**
         * The Retrofit class generates an implementation of the API interface.
         */
        if (service == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.reddit.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            service = retrofit.create(RetrofitAPI::class.java)
        }

        return service
    }

    fun getRedditPost(mIOnListPostReady: IOnListPostReady) {
        val posts  = getRService()?.getPosts()
        posts?.enqueue(object : Callback<JSonObject> {

            override fun onResponse(call:Call<JSonObject>, response: Response<JSonObject>) {
                if(response.body()!= null){
                    val exampleObject= response.body()
                    if(response.code() == 200){
                        val size = exampleObject.data?.children?.size!!
                        if(size > 0){
                            val list = exampleObject.data?.children
                            list?.forEach { child -> mPostList.add(child)}
                            mIOnListPostReady.onPostsDone(mPostList)
                        }
                    }
                }
            }

            override fun onFailure(call:Call<JSonObject>, t: Throwable) {
                Log.d("RetrofitController", "Everything was wrong")
            }
        })
    }
}