package com.yafateme.app.yafateme.mydb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AboutDao {

    @Insert
    fun insert(item: About)

    @Delete
    fun delete(item: About)

    @Query("SELECT * FROM about")
    fun getAll() : List<About>

    // @Query("SELECT * FROM madah_song")
    //  fun getAll() : List<MadahSong>

    //@Query("SELECT * FROM item_table WHERE category LIKE :categoryName")
    //fun findByCategory(categoryName: String) : List<Item>

}