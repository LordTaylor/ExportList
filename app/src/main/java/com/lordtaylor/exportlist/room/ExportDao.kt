package com.lordtaylor.exportlist.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lordtaylor.exportlist.model.ExportItem

@Dao
interface ExportDao {

    @Query("SELECT * FROM ExportItem WHERE name LIKE :search OR user LIKE :search OR date LIKE :search OR location LIKE :search")
    fun getAll(search:String="%"): LiveData<List<ExportItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ExportItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(item: List<ExportItem>?)

    @Query("DELETE from ExportItem")
    fun deleteAll()
}