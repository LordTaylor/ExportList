package com.lordtaylor.exportlist.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.lordtaylor.exportlist.model.ExportItem
import com.lordtaylor.exportlist.repository.ExportRepository

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    var repo: ExportRepository

    lateinit var exportItems: LiveData<List<ExportItem>>
    init {
        repo = ExportRepository(application.baseContext)
    }

    fun getAllItems():LiveData<List<ExportItem>> {
        exportItems=repo.getAllItems()
        return  exportItems
    }
}