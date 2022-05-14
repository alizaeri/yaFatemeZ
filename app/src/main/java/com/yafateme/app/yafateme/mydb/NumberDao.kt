package com.yafateme.app.yafateme.mydb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NumberDao {

    @Insert
    fun insert(item: Number)

    @Delete
    fun delete(item: Number)

    @Query("SELECT * FROM number")
    fun getAll(): List<Number>

// @Query("SELECT * FROM madah_song")
//  fun getAll() : List<MadahSong>

//@Query("SELECT * FROM item_table WHERE category LIKE :categoryName")
//fun findByCategory(categoryName: String) : List<Item>

}