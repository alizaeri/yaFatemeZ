package com.yafateme.app.yafateme.mydb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ About::class
                     , MadahSong::class,
                        Doa::class,
                        Lang::class,
                        Language::class,
                        Number::class,
                        LangApp::class

                     ] , version = 3)
abstract class AppDatabase : RoomDatabase(){
    abstract fun aboutDao() : AboutDao
    abstract fun madahSongDao() : MadahSongDao
    abstract fun doaDao() : DoaDao
    abstract fun langDao() : LangDao
    abstract fun languageDao() : LanguageDao
    abstract fun numberDao() : NumberDao
    abstract fun langAppDao() : LangAppDao




}