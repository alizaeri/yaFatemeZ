package com.yafateme.app.yafateme.mydb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MadahSongDao {

    @Insert
    fun insert(item: MadahSong)

    @Delete
    fun delete(item: MadahSong)

    // @Query("SELECT * FROM item_table")
    // fun getAll() : List<Item>

    @Query("SELECT * FROM madah_song")
    fun getAll() : List<MadahSong>

   // @Query("SELECT * FROM madah_song WHERE id LIKE :categoryName")
    //fun findByCategory(categoryName: Int) : List<MadahSong>

}