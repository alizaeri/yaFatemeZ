package com.yafateme.app.yafateme.mydb


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "about")
data class About(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "dbText") var dbText: String,

)