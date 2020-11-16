package com.fernando.reddito.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fernando.reddito.R
import kotlinx.android.synthetic.main.fragment_post_detail.view.*

class PostDetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_post_detail, container, false)

        val fragmentName = arguments?.getString("fragmentName")

        rootView.fragment_name.text = fragmentName

        return rootView
    }
}