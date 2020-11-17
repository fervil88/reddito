package com.fernando.reddito.db

object SQLiteHelper {

        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "REDDITO.db"
        const val PASSPHRASE = "PASSPHRASE_REDDITO"

        const val POST_TABLE_NAME = "posts"
        const val COLUMN_ID_POST = "_id"

        const val CREATE_POST_TABLE = ("CREATE TABLE " +
                POST_TABLE_NAME + "("
                + COLUMN_ID_POST + " TEXT PRIMARY KEY" + ")" )
}