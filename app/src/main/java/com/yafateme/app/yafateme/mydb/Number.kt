package com.yafateme.app.yafateme.mydb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "number")
data class Number(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "fa") var fa: String?,
    @ColumnInfo(name = "en") var en: String?,


    )