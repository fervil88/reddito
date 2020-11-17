package com.fernando.reddito.db

import android.content.Context

object PostRepositoryManager {

    lateinit var mDBSQLiteHelper: DBSQLiteHelper

    fun initialize(context: Context) {
        mDBSQLiteHelper = DBSQLiteHelper(context, null)
    }
}