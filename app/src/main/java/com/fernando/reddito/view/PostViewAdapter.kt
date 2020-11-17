package com.fernando.reddito.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fernando.reddito.R
import com.fernando.reddito.model.Child
import com.fernando.reddito.model.Post
import com.squareup.picasso.Picasso

class PostViewAdapter (
    private var posts : MutableList<Child>,
    private val listener : (Post) -> Unit) : RecyclerView.Adapter<BaseViewHolder>() {

    private var isLoaderVisible = false

    companion object {
        const val TAG = "PostViewAdapter"
        const val VIEW_TYPE_LOADING = 0
        const val VIEW_TYPE_NORMAL = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return when (viewType) {
            VIEW_TYPE_LOADING -> ProgressHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false))
            VIEW_TYPE_NORMAL -> PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post_view, parent, false), posts)
            else -> PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post_view, parent, false), posts)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoaderVisible) {
            if (position == posts.size - 1) VIEW_TYPE_LOADING else VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_NORMAL
        }
    }

    override fun getItemCount() = posts.size


    fun getItem(position: Int): Child {
        return posts[position]
    }

    fun clear() {
        posts.clear()
        notifyDataSetChanged()
    }

    fun addLoading() {
        isLoaderVisible = true
        posts.add(Child()) //For loading progress bar
        notifyItemInserted(posts.size - 1)
    }

    fun removeLoading() {
        isLoaderVisible = false
        val position = posts.size - 1
        posts.removeAt(position)
        notifyItemRemoved(position)
    }

    fun updatePost(list: MutableList<Child>) {
        posts.addAll(list)
        notifyDataSetChanged()
    }

    class PostViewHolder(itemView: View, private var posts : MutableList<Child> ) : BaseViewHolder(itemView) {
        override fun clear() {}

        private var mCurrentPosition: Int = 0

        override fun onBind(position: Int) {
            mCurrentPosition = position

            val title: TextView = itemView.findViewById(R.id.title_text_item)
            val author: TextView = itemView.findViewById(R.id.author_text_item)
            val date: TextView = itemView.findViewById(R.id.date_text_item)
            val thumbnail: ImageView = itemView.findViewById(R.id.thumb_image_item)
            val numberComments: TextView = itemView.findViewById(R.id.number_comments_text_item)

            val currentPost = posts[position]
            title.text = currentPost.dataChild?.title
            author.text = currentPost.dataChild?.author
            date.text = currentPost.dataChild?.created.toString()
            numberComments.text = currentPost.dataChild?.numComments.toString()

            val url = currentPost.dataChild?.url
            if (url != null) {
                if (url.isNotEmpty()) {
                    Picasso.with(thumbnail.context).cancelRequest(thumbnail)
                    Picasso.with(thumbnail.context).load(url)
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .into(thumbnail)
                }
            }
        }
    }

    class ProgressHolder(itemView: View) : BaseViewHolder(itemView) {

        override fun onBind(position: Int) {}

        override fun clear() {}
    }

}