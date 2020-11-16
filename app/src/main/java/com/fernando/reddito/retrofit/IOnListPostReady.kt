package com.fernando.reddito.retrofit

import com.fernando.reddito.model.Post

interface IOnListPostReady {

    fun onPostsDone(list: MutableList<Post>)

}