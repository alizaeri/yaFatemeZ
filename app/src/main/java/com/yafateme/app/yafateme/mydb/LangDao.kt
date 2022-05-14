package com.yafateme.app.yafateme.mydb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LangDao {

    @Insert
    fun insert(item: Lang)

    @Delete
    fun delete(item: Lang)

    @Query("SELECT * FROM lang")
    fun getAll() : List<Lang>

    // @Query("SELECT * FROM madah_song")
    //  fun getAll() : List<MadahSong>

    //@Query("SELECT * FROM item_table WHERE category LIKE :categoryName")
    //fun findByCategory(categoryName: String) : List<Item>

}