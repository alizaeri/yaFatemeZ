package com.yafateme.app.yafateme.mydb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LanguageDao {

    @Insert
    fun insert(item: Language)

    @Delete
    fun delete(item: Language)

    @Query("SELECT * FROM language")
    fun getAll() : List<Language>

    // @Query("SELECT * FROM madah_song")
    //  fun getAll() : List<MadahSong>

    //@Query("SELECT * FROM item_table WHERE category LIKE :categoryName")
    //fun findByCategory(categoryName: String) : List<Item>

}