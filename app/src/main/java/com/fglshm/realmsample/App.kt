package com.fglshm.realmsample

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class App : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        var mContext: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
    }

}