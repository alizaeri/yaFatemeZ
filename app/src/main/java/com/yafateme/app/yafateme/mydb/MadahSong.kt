package com.yafateme.app.yafateme.mydb


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Madah_song")

data class MadahSong (

    @ColumnInfo(name = "song1") var song1: Int?,
    @ColumnInfo(name = "song2") var song2: Int?,
    @ColumnInfo(name = "song3") var song3: Int?,

    @PrimaryKey(autoGenerate = true) val id: Int


)

