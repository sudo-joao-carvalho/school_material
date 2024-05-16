package pt.isec.ans.teostorage.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context : Context) :
        SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "mydb.db"
        private const val DATABASE_VERSION = 1
    }
    val createCmd = "CREATE TABLE people ( id NUMBER, name TEXT, age NUMBER );"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createCmd)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { }
}

