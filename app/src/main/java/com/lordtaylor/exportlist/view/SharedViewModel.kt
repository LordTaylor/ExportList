package com.lordtaylor.exportlist.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.lordtaylor.exportlist.repository.ExportRepository

class SharedViewModel(application: Application) :AndroidViewModel(application) {

    lateinit var repo:ExportRepository
}