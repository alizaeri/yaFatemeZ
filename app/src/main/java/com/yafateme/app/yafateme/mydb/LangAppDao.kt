package com.yafateme.app.yafateme.mydb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LangAppDao {

    @Insert
    fun insert(item: LangApp)

    @Delete
    fun delete(item: LangApp)

    @Query("SELECT * FROM langapp")
    fun getAll() : List<LangApp>

    // @Query("SELECT * FROM madah_song")
    //  fun getAll() : List<MadahSong>

    //@Query("SELECT * FROM item_table WHERE category LIKE :categoryName")
    //fun findByCategory(categoryName: String) : List<Item>

}