package com.yafateme.app.yafateme

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.yafateme.app.yafateme.mydb.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.io.File
import kotlin.coroutines.CoroutineContext

class G : Application() {






    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        preference = PreferenceManager.getDefaultSharedPreferences(context)



       // DataBase_old_Version = preference.getInt("OLD_DATABASE", DataBase_old_Version)
        //DataBase_new_Version = preference.getInt("currect_DATABASE", DataBase_new_Version)
/*

        if (DataBase_old_Version < DataBase_new_Version) {
            val dir: File = File(database.path)
            if (dir.isDirectory) {
                val children = dir.list()
                for (i in children.indices) {
                    File(dir, children[i]).delete()
                }
            }
            val editor = preference.edit()
            editor.putInt("OLD_DATABASE", DataBase_new_Version)
            editor.commit()
        }
        */

    }

    companion object {
       // var DataBase_old_Version = 3
       // var DataBase_new_Version = 4
        lateinit var preference: SharedPreferences
        lateinit var context: Context
        var inflater: LayoutInflater? = null

     }
}

