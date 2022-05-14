package com.yafateme.app.yafateme.mydb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DoaDao {

    @Insert
    fun insert(item: Doa)

    @Delete
    fun delete(item: Doa)

    @Query("SELECT * FROM doa")
    fun getAll() : List<Doa>

    // @Query("SELECT * FROM madah_song")
    //  fun getAll() : List<MadahSong>

    //@Query("SELECT * FROM item_table WHERE category LIKE :categoryName")
    //fun findByCategory(categoryName: String) : List<Item>

}