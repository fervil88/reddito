package com.fernando.reddito.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fernando.reddito.R
import com.fernando.reddito.model.Child
import com.fernando.reddito.util.Constants.DEFAULT_THUMBNAIL
import com.squareup.picasso.Picasso
import android.view.animation.AnimationUtils.loadAnimation
import android.os.Handler
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import android.R



class PostViewAdapter( private var posts: MutableList<Child>, private var listenerClickView: (Int) -> Unit
) : RecyclerView.Adapter<BaseViewHolder>() {

    private var isLoaderVisible = false
    private lateinit var context: Context

    companion object {
        val TAG = PostViewAdapter::class.simpleName
        const val VIEW_TYPE_LOADING = 0
        const val VIEW_TYPE_NORMAL = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        context = parent.context
        return when (viewType) {
            VIEW_TYPE_LOADING -> ProgressHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false))
           VIEW_TYPE_NORMAL -> PostViewHolder(LayoutInflater.from(parent.context)
               .inflate(R.layout.item_post_view, parent, false), posts, listenerClickView) { view, position -> remove(view, position ) }
           else -> PostViewHolder(LayoutInflater.from(parent.context)
               .inflate(R.layout.item_post_view, parent, false), posts, listenerClickView) { view, position -> remove(view, position ) }
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

    private fun remove1(position: Int) {
        val child = getItem(position)
        Log.d(TAG, "POSITION: $position - TITLE: ${child.dataChild?.title}")
        posts.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun remove(rowView: View, position: Int) {

        val anim = loadAnimation( context, android.R.anim.slide_out_right)
        anim.duration = 300
        rowView.startAnimation(anim)

        Handler().postDelayed( {
            if (posts.size != 0) {
                posts.removeAt(position) //Remove the current content from the array
                notifyDataSetChanged() //Refresh list
            }
        }, anim.duration)
    }

    class PostViewHolder(itemView: View, private var posts: MutableList<Child>,
                         private val listenerClickView: (Int) -> Unit,
                         private val listenerClickButton: (View, Int) -> Unit) : BaseViewHolder(itemView) {
        override fun clear() {}

        private var mCurrentPosition: Int = 0

        override fun onBind(position: Int) {
            mCurrentPosition = position

            //UI
            val title: TextView = itemView.findViewById(R.id.title_text_item)
            val author: TextView = itemView.findViewById(R.id.author_text_item)
            val date: TextView = itemView.findViewById(R.id.date_text_item)
            val thumbnail: ImageView = itemView.findViewById(R.id.thumb_image_item)
            val titleURL: TextView = itemView.findViewById(R.id.title_url)
            val numberComments: TextView = itemView.findViewById(R.id.number_comments_text_item)
            val notification: ImageView = itemView.findViewById(R.id.notification)
            val dismissButton: Button = itemView.findViewById(R.id.button_dismiss)

            val currentPost = posts[position]
            title.text = currentPost.dataChild?.title
            author.text = currentPost.dataChild?.author
            date.text = currentPost.dataChild?.created.toString()
            numberComments.text = currentPost.dataChild?.numComments.toString()
            notification.visibility = if (currentPost.isRead ) View.GONE else View.VISIBLE

            var urlthumbnail = currentPost.dataChild?.thumbnail
            if (urlthumbnail != DEFAULT_THUMBNAIL) {

                Picasso.with(thumbnail.context).cancelRequest(thumbnail)
                Picasso.with(thumbnail.context).load(urlthumbnail)
                    .into(thumbnail)
            } else {
                thumbnail.visibility = View.GONE
                urlthumbnail = currentPost.dataChild?.urlOverriddenByDest
                titleURL.visibility = View.VISIBLE
                titleURL.text = urlthumbnail
            }

            itemView.setOnClickListener { listenerClickView(position) }
            dismissButton.setOnClickListener { listenerClickButton (itemView, position) }
        }
    }

    class ProgressHolder(itemView: View) : BaseViewHolder(itemView) {

        override fun onBind(position: Int) {}

        override fun clear() {}
    }

}