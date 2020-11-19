package com.fernando.reddito.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fernando.reddito.R
import com.fernando.reddito.model.Child
import com.fernando.reddito.util.Constants.DEFAULT_THUMBNAIL
import com.fernando.reddito.util.Constants.SELECTED_POST
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_post_detail.view.*

class PostDetailFragment : Fragment() {

    companion object {
        val TAG = PostDetailFragment::class.simpleName
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {

        val rootView = inflater.inflate(R.layout.fragment_post_detail, container, false)
        val currentPost = arguments?.getSerializable(SELECTED_POST) as Child

        rootView.title_text_item.text = currentPost.dataChild?.title
        rootView.author_text_item.text = currentPost.dataChild?.author
        rootView.date_text_item.text = currentPost.dataChild?.created.toString()
        rootView.number_comments_text_item.text = currentPost.dataChild?.numComments.toString()

        val thumbnail = currentPost.dataChild?.thumbnail
        if (thumbnail != DEFAULT_THUMBNAIL) {
            val url = currentPost.dataChild?.url

            Picasso.with(rootView.thumb_image_item.context).cancelRequest(rootView.thumb_image_item)
            Picasso.with(rootView.thumb_image_item.context).load(url)
                .into(rootView.thumb_image_item)
        } else {
            rootView.thumb_image_item.visibility = View.GONE
            val url = currentPost.dataChild?.urlOverriddenByDest
            rootView.title_url.visibility = View.VISIBLE
            rootView.title_url.text = url
        }

        return rootView
    }
}