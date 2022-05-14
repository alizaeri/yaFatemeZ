package com.yafateme.app.yafateme.mydb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "language")
data class Language(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "lang0") var lang0: String?,
    @ColumnInfo(name = "lang1") var lang1: String?,
    @ColumnInfo(name = "lang2") var lang2: String?,
    @ColumnInfo(name = "lang3") var lang3: String?,
    @ColumnInfo(name = "trl") var trl: String?,
    @ColumnInfo(name = "trl1_0") var trl1_0: String?,
    @ColumnInfo(name = "trl1_1") var trl1_1: String?,
    @ColumnInfo(name = "trl1_2") var trl1_2: String?,
    @ColumnInfo(name = "trl1_3") var trl1_3: String?,
    @ColumnInfo(name = "trl2_0") var trl2_0: String?,
    @ColumnInfo(name = "trl2_1") var trl2_1: String?,
    @ColumnInfo(name = "trl2_2") var trl2_2: String?,
    @ColumnInfo(name = "trl2_3") var trl2_3: String?,
    @ColumnInfo(name = "name0") var name0: String?,
    @ColumnInfo(name = "name1") var name1: String?,
    @ColumnInfo(name = "name2") var name2: String?,
    @ColumnInfo(name = "name3") var name3: String?,
    @ColumnInfo(name = "url") var url: String?,

    )