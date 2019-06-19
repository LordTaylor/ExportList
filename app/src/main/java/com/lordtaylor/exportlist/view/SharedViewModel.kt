package com.lordtaylor.exportlist.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.lordtaylor.exportlist.model.ExportItem
import com.lordtaylor.exportlist.repository.ExportRepository
import com.lordtaylor.exportlist.view.dialogs.AppDialog.*

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    private var repo: ExportRepository

    lateinit var exportItems: LiveData<List<ExportItem>>
    init {
        repo = ExportRepository(application.baseContext)
    }

    fun getAllItems():LiveData<List<ExportItem>> {
        exportItems=repo.getAllItems()
        return  exportItems
    }

    fun searchForName(type:Type,name: String):LiveData<List<ExportItem>> {

        when (type) {
            Type.NAME -> {
                exportItems = repo.getNameFilter(name)
                return exportItems

            }
            Type.USER -> {
                exportItems = repo.getUsersFilter(name)
                return exportItems
            }
            Type.LOCATION -> {
                exportItems = repo.getLocations(name)
                return exportItems
            }
            else -> {
                exportItems = repo.getDates(name)
                return exportItems
            }
        }
    }
}