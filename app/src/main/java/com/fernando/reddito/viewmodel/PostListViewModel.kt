package com.fernando.reddito.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.fernando.reddito.db.PostRepositoryManager
import com.fernando.reddito.model.Child
import com.fernando.reddito.retrofit.IOnListPostReady
import com.fernando.reddito.retrofit.RetrofitController
import net.sqlcipher.database.SQLiteDatabase

class PostListViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        const val TAG = "PostListViewModel"
    }

    private val mRetrofitController = RetrofitController()
    private var itemCount = 0

    val mLivePostList: MutableLiveData<MutableList<Child>> = MutableLiveData()

    var mPostList: MutableList<Child> = mutableListOf()

    private val mIOnListPostReady = object : IOnListPostReady {
        override fun onPostsDone(list: MutableList<Child>) {
            list.forEach { child -> child.isRead = PostRepositoryManager.mDBSQLiteHelper.isIDRead(
                child.dataChild?.id!!
            ) }
            mPostList = list
        }
    }

    init {
        mRetrofitController.getRedditPost(mIOnListPostReady)
        SQLiteDatabase.loadLibs(getApplication<Application>().applicationContext)
        PostRepositoryManager.initialize(getApplication<Application>().applicationContext)
    }

    fun postRead(id: String) {
        PostRepositoryManager.mDBSQLiteHelper.addPostID(id)
    }

    fun loadNextPagePost() {
        val items = arrayListOf<Child>()
        for (i in 0..4) {
            if (itemCount < mPostList.size) {
                val post = mPostList[itemCount]
                items.add(post)
            }
            itemCount++
        }
        mLivePostList.value = items
    }

    fun reset() {
        itemCount = 0
    }

}