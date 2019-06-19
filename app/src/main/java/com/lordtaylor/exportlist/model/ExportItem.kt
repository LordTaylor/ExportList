package com.lordtaylor.exportlist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(indices = arrayOf(Index(value = ["mid"], unique = true)))
data class ExportItem(
    @ColumnInfo(name = "mid")
    @PrimaryKey(autoGenerate = true)
    var _id: Long,
    var name: String,
    var date: String,
    var user: String,
    var location: String
) : BaseItem {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ExportItem

        if (_id != other._id) return false
        if (name != other.name) return false
        if (date != other.date) return false
        if (user != other.user) return false
        if (location != other.location) return false

        return true
    }

    override fun hashCode(): Int {
        var result = _id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + user.hashCode()
        result = 31 * result + location.hashCode()
        return result
    }

}