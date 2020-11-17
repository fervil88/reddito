package com.fernando.reddito.db

import android.content.ContentValues
import android.content.Context
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SQLiteOpenHelper
import android.util.Log
import com.fernando.reddito.db.SQLiteHelper.COLUMN_ID_POST
import com.fernando.reddito.db.SQLiteHelper.CREATE_POST_TABLE
import com.fernando.reddito.db.SQLiteHelper.DATABASE_NAME
import com.fernando.reddito.db.SQLiteHelper.DATABASE_VERSION
import com.fernando.reddito.db.SQLiteHelper.PASSPHRASE
import com.fernando.reddito.db.SQLiteHelper.POST_TABLE_NAME

class DBSQLiteHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object {
        const val TAG = "DBSQLiteHelper"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_POST_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.d(TAG, "oldVersion=$oldVersion - newVersion=$newVersion")
    }

    fun addPostID(_id: String) {
        val values = ContentValues()
        values.put(COLUMN_ID_POST, _id)

        val db = this.getWritableDatabase(PASSPHRASE)
        db.insert(POST_TABLE_NAME, null, values)

    }

    fun isIDRead(id: String): Boolean {
        val db = this.getWritableDatabase(PASSPHRASE)
        val cursor = db.rawQuery("SELECT * FROM $POST_TABLE_NAME WHERE ($COLUMN_ID_POST == '$id')", null)
        return cursor.count == 1
    }
}