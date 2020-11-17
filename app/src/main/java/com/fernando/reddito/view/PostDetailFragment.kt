package com.fernando.reddito.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.fernando.reddito.R
import com.fernando.reddito.model.Child
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_post_detail.view.*

class PostDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_post_detail, container, false)

        val currentPost = arguments?.getSerializable("post") as Child

        rootView.title_text_item.text = currentPost.dataChild?.title
        rootView.author_text_item.text = currentPost.dataChild?.author
        rootView.date_text_item.text = currentPost.dataChild?.created.toString()
        rootView.number_comments_text_item.text = currentPost.dataChild?.numComments.toString()

        val url = currentPost.dataChild?.url
        if (url != null) {
            if (url.isNotEmpty()) {
                Picasso.with(rootView.thumb_image_item.context).cancelRequest(rootView.thumb_image_item)
                Picasso.with(rootView.thumb_image_item.context).load(url)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(rootView.thumb_image_item)
            }
        }

        return rootView
    }
}