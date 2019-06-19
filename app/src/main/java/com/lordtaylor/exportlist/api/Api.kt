package com.lordtaylor.exportlist.api

import com.google.gson.GsonBuilder
import com.lordtaylor.exportlist.utils.Constance
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface Api {

    @GET("/ExportList.json")
    fun getExports(): Single<String>


    companion object {
        fun create(): Api {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Constance.BASE_URL)
                .build()
            return retrofit.create(Api::class.java)
        }
    }
}