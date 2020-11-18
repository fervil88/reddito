package com.fernando.reddito.viewmodel

import android.app.Application
import android.os.Environment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.fernando.reddito.db.PostRepositoryManager
import com.fernando.reddito.model.Child
import com.fernando.reddito.retrofit.IOnListPostReady
import com.fernando.reddito.retrofit.RetrofitController
import net.sqlcipher.database.SQLiteDatabase
import android.os.Environment.getExternalStorageDirectory
import android.util.Log
import java.io.FileOutputStream
import java.net.URL
import android.provider.MediaStore.Images
import android.provider.MediaStore
import android.content.ContentValues
import android.content.Context


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

    fun storeImage(urlString: String) {
        val url = URL(urlString)
        val input = url.openStream()
        input.use { inputStream ->
            val storagePath = getApplication<Application>().applicationContext.getExternalFilesDir(null)
            Log.d(TAG, "PATH: ${storagePath?.path} + ${url.file}")
            val path = storagePath?.path + url.file
            val output = FileOutputStream(path)
            output.use { outputStream ->
                val buffer = ByteArray(8192)
                var bytesRead = 0
                while ((inputStream.read(buffer, 0, buffer.size).also { bytesRead = it }) >= 0) {
                    outputStream.write(buffer, 0, bytesRead)
                }
            }
            addImageToGallery(path, getApplication<Application>().applicationContext)
        }
    }

    private fun addImageToGallery(filePath: String, context: Context) {
        val values = ContentValues()
        values.put(Images.Media.DATE_ADDED, System.currentTimeMillis())
        values.put(Images.Media.MIME_TYPE, "image/jpeg")
        values.put(Images.Media._ID, filePath)

        context.contentResolver.insert(Images.Media.EXTERNAL_CONTENT_URI, values)
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