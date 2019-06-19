package com.lordtaylor.exportlist.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.lordtaylor.exportlist.api.Api
import com.lordtaylor.exportlist.model.ExportItem
import com.lordtaylor.exportlist.room.ExportDatabase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ExportRepository(var context: Context) {
    private val TAG = "ExportRepository"

    fun getAllItems(): LiveData<List<ExportItem>> {
        return database.getDao().getAll()
    }

    var api: Api
    var database: ExportDatabase

    init {
        database = ExportDatabase.getInstance(context)!!
        api = Api.create()
        api.getExports().subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.forEach({Log.d(TAG,"item in db : ${it._id}")})
                addToDb(it)
            }, {
                Log.e(TAG, "error retrofit :Message ${it.localizedMessage}")
            })
    }

    private fun addToDb(items: List<ExportItem>?) {

        Observable.fromCallable{database.getDao().insertAll(items)}.
            subscribeOn(Schedulers.io()).
            observeOn(AndroidSchedulers.mainThread()).
            subscribe({

            },{
                Log.e(TAG, "error ROOM :Message ${it.localizedMessage}")
            })

    }

    fun getNameFilter(name: String): LiveData<List<ExportItem>> {
        return database.getDao().getNames(name)
    }

    fun getUsersFilter(users: String): LiveData<List<ExportItem>> {
        return database.getDao().getUsers(users)
    }

    fun getLocations(locations: String): LiveData<List<ExportItem>> {
        return database.getDao().getLocations(locations)
    }

    fun getDates(dates: String): LiveData<List<ExportItem>> {
        return database.getDao().getDates(dates)
    }


}