package com.lordtaylor.exportlist.repository

import android.content.Context
import com.lordtaylor.exportlist.api.Api
import com.lordtaylor.exportlist.room.ExportDatabase

class ExportRepository(var context: Context) {

     var api:Api
     var database:ExportDatabase

    init {
        database = ExportDatabase.getInstance(context)!!
        api = Api.create()
    }
}