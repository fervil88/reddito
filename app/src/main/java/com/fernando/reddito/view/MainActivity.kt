package com.fernando.reddito.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fernando.reddito.R
import com.fernando.reddito.databinding.ActivityMainBinding
import com.fernando.reddito.model.Child
import com.fernando.reddito.model.Post
import com.fernando.reddito.viewmodel.PostListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mPostListViewModel: PostListViewModel
    private lateinit var mAdapter: PostViewAdapter

    private val mListPostObserver: Observer<MutableList<Child>> = Observer {
        mAdapter.updatePost(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mPostListViewModel = ViewModelProvider(this).get(PostListViewModel::class.java)

        layoutManager = LinearLayoutManager(this)
        mBinding.navigationRecyclerView.layoutManager = layoutManager
        mBinding.navigationRecyclerView.setHasFixedSize(true)

    }

    override fun onResume() {
        super.onResume()

        mAdapter = PostViewAdapter(mPostListViewModel.mPostList){}
        mBinding.navigationRecyclerView.adapter = mAdapter

        mPostListViewModel.mLivePostList.observe(this, mListPostObserver)

    }
}
