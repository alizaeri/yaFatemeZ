package com.yafateme.app.yafateme

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
/*
class database(private val mycontext: Context) :
    SQLiteOpenHelper(mycontext, "db", null, DATABASE_VERSION) {
    lateinit var mydb: SQLiteDatabase
    override fun onCreate(arg0: SQLiteDatabase) {}
    override fun onUpgrade(arg0: SQLiteDatabase, arg1: Int, arg2: Int) {}
    fun useable() {
        val checkdb = checkdb()
        if (checkdb) {
        } else {
            this.readableDatabase
            try {
                copydatabase()
            } catch (e: IOException) {
            }
        }
    }

    fun open() {
        mydb = SQLiteDatabase.openDatabase(path + Name, null, SQLiteDatabase.OPEN_READWRITE)
    }

    override fun close() {
        mydb!!.close()
    }

    fun checkdb(): Boolean {
         lateinit var db: SQLiteDatabase
        try {
            db = SQLiteDatabase.openDatabase("/data/data/com.yafateme.app.yafateme/databases/database", null, SQLiteDatabase.OPEN_READONLY)


        } catch (e: SQLException) {
        }
        return if (db != null) true else false
    }

    @Throws(IOException::class)
    fun copydatabase() {
        val myOutput: OutputStream = FileOutputStream(path + Name)
        val buffer = ByteArray(1024)
        var lenght: Int
        val myInput = mycontext.assets.open(Name)
        while (myInput.read(buffer).also { lenght = it } > 0) {
            myOutput.write(buffer, 0, lenght)
        }
        myInput.close()
        myOutput.flush()
        myOutput.close()
    }

    fun namayesh(row: Int, field: Int, table: String): String {
        val Cursor = mydb!!.rawQuery("SELECT * FROM $table", null)
        Cursor.moveToPosition(row)
        return Cursor.getString(field)
    }

    //-------------------- z	ظاهرا این خط برای لیست علاقه مندی ها است
    fun Fav_update(table: String?, id: String, value: String?) {
        val cv = ContentValues()
        cv.put("fav", value)
        mydb!!.update(table, cv, "id='$id'", null)
    }

    //------------------------c 	این خط هم برای آپدیت هست ولی استفاده نشده است
    fun delete_v1_database() {
        val delete_old_database = G.preference.getBoolean("DELETE_OLD_DATABASE", false)
        if (!delete_old_database) {
            // Toast.makeText(this, "welcome", Toast.LENGTH_SHORT).show();
            val dir = File(path)
            if (dir.isDirectory) {
                val children = dir.list()
                for (i in children.indices) {
                    File(dir, children[i]).delete()
                }
            }
            val editor = G.preference.edit()
            editor.putBoolean("DELETE_OLD_DATABASE", true)
            editor.commit()
        }
    }

    /**
     * public void delete_v1_database() {
     * boolean delete_old_database = G.preference.getBoolean("DELETE_OLD_DATABASE", false);
     * if ( !delete_old_database) {
     * // Toast.makeText(this, "welcome", Toast.LENGTH_SHORT).show();
     *
     * File dir = new File(DBLOCATION);
     * if (dir.isDirectory())
     * {
     * String[] children = dir.list();
     * for (int i = 0; i < children.length; i++)
     * {
     * new File(dir, children[i]).delete();
     * }
     * }
     * SharedPreferences.Editor editor = G.preference.edit();
     * editor.putBoolean("DELETE_OLD_DATABASE", true);
     * editor.commit();
     *
     * }
     * }   */
    companion object {
        val path = "/data/data/" + "com.yafateme.app.yafateme" + "/databases/"
        const val Name = "database"
        private val DATABASE_VERSION = G.DataBase_new_Version
    }
}

 */