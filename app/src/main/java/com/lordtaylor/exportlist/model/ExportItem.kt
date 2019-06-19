package com.lordtaylor.exportlist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(indices = arrayOf(Index(value = ["id"], unique = true)))
data class ExportItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var _id: Long,
    var name: String,
    var date: String,
    var user: String,
    var location: String
) : BaseItem