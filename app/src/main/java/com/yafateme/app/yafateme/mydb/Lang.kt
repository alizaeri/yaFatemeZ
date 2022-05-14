package com.yafateme.app.yafateme.mydb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lang")
data class Lang(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "translateFa") var translateFa: String?,
    @ColumnInfo(name = "translateAr") var translateAr: String?,
    @ColumnInfo(name = "translateEn") var translateEn: String?,
    @ColumnInfo(name = "translateUr") var translateUr: String?,

    )