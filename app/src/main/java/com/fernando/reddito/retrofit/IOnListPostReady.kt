package com.fernando.reddito.retrofit

import com.fernando.reddito.model.Child

interface IOnListPostReady {

    fun onPostsDone(list: MutableList<Child>)

}