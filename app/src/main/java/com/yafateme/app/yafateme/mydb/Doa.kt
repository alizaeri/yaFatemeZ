package com.yafateme.app.yafateme.mydb



import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doa")
data class Doa(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "text") var text: String,
    @ColumnInfo(name = "translateFa") var translateFa: String,
    @ColumnInfo(name = "translateEn") var translateEn: String,
    @ColumnInfo(name = "translateUrdu") var translateUrdu: String,

    )