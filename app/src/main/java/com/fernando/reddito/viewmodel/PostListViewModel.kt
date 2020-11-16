package com.fernando.reddito.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fernando.reddito.model.Child
import com.fernando.reddito.retrofit.IOnListPostReady
import com.fernando.reddito.retrofit.RetrofitController

class PostListViewModel : ViewModel() {

    companion object {
        const val TAG = "PostListViewModel"
    }

    private val mRetrofitController = RetrofitController()

    val mLivePostList: MutableLiveData<MutableList<Child>> = MutableLiveData()

    var mPostList: MutableList<Child> = mutableListOf()

    private val mIOnListPostReady = object : IOnListPostReady {
        override fun onPostsDone(list: MutableList<Child>) {
            mPostList = list
        }
    }

    init {
        mRetrofitController.getRedditPost(mIOnListPostReady)
    }

}