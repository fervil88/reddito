package com.fernando.reddito.model

class Post (
    var id: String = "-1",
    var mTitle: String,
    var mAuthor: String,
    var mEntryDate: String,
    var mThumbnail: String,
    var mNumberOfComments: Int
) {
    var mUnreadStatus: Boolean = false


    companion object {
        fun createDummy(): Post {
            val id = "kjsdbks"
            val mTitle = "mTitle"
            val mAuthor = "mAuthor"
            val mEntryDate = "10/20/2020"
            val mThumbnail =
                "https://b.thumbs.redditmedia.com/eVnrtU9aX9Dd_zFnKpmBxzpTG5BmJ7Xda6QfkDu8itU.jpg"
            val mNumberOfComments = 450

            return Post(id, mTitle, mAuthor, mEntryDate, mThumbnail, mNumberOfComments)
        }

    }
}