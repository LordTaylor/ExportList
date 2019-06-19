package com.lordtaylor.exportlist.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lordtaylor.exportlist.model.ExportItem

@Dao
interface ExportDao {

    @Query("SELECT * FROM ExportItem ")
    fun getAll(): LiveData<List<ExportItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ExportItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(item: List<ExportItem>?)

    @Query("DELETE from ExportItem")
    fun deleteAll()

    @Query("SELECT * FROM ExportItem WHERE name LIKE :name ")
    fun getNames(name: String): LiveData<List<ExportItem>>

    @Query("SELECT * FROM ExportItem WHERE user LIKE :users ")
    fun getUsers(users: String): LiveData<List<ExportItem>>

    @Query("SELECT * FROM ExportItem WHERE location LIKE :locations ")
    fun getLocations(locations: String): LiveData<List<ExportItem>>

    @Query("SELECT * FROM ExportItem WHERE date LIKE :dates")
    fun getDates(dates: String): LiveData<List<ExportItem>>
}