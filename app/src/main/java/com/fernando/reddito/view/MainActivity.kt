package com.fernando.reddito.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.fernando.reddito.R
import com.fernando.reddito.databinding.ActivityMainBinding
import com.fernando.reddito.model.Child
import com.fernando.reddito.model.Post
import com.fernando.reddito.view.PaginationScrollListener.Companion.PAGE_START
import com.fernando.reddito.viewmodel.PostListViewModel

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private var currentPage = PAGE_START
    private var isLastPage = false
    private var totalPage = 5
    private var isLoading = false

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

        mBinding.navigationRecyclerView.addOnItemTouchListener(RecyclerTouchListener(this, object : ClickListener {
            override fun onClick(view: View, position: Int) {

                // # Home Fragment
                val bundle = Bundle()
                bundle.putSerializable("fragmentName", "Detail Fragment")
                val homeFragment = PostDetailFragment()
                homeFragment.arguments = bundle
                supportFragmentManager.beginTransaction()
                    .replace(R.id.activity_main_content_id, homeFragment).commit()

                Handler().postDelayed({
                    mBinding.drawerLayout.closeDrawer(GravityCompat.START)
                }, 200)
            }
        }))
        mBinding.navigationRecyclerView.addOnScrollListener(object : PaginationScrollListener(layoutManager) {

            override fun loadMoreItems() {
                isLoading = true
                currentPage++
                loadMorePosts()
            }
            override fun isLastPage() = isLastPage
            override fun isLoading() = isLoading
        })

        mBinding.navigationLayout.setOnRefreshListener(this)

        mPostListViewModel.mLivePostList.observe(this, mListPostObserver)
    }

    override fun onRefresh() {
        mPostListViewModel.reset()
        currentPage = PAGE_START
        isLastPage = false
        mAdapter.clear()
        loadMorePosts()
    }

    private fun loadMorePosts() {
        Handler().postDelayed( {
            mBinding.navigationLayout.isRefreshing = false
            if (currentPage != PAGE_START) mAdapter.removeLoading()
            mPostListViewModel.loadNextPagePost()

            if (currentPage <= totalPage) {
                mAdapter.addLoading()
            } else {
                isLastPage = true
            }

            isLoading = false
        }, 1500)
    }
}
