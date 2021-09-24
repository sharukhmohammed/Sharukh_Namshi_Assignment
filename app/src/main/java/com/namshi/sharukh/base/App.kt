package com.namshi.sharukh.base

import android.app.Application
import io.reactivex.rxjava3.exceptions.UndeliverableException
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import timber.log.Timber

class App : Application() {

    companion object {

        @get:Synchronized
        lateinit var instance: App
            private set
    }


    override fun onCreate() {
        super.onCreate()
        instance = this


        initTimber()
        initRxErrorHandler()
    }

    private fun initTimber() {
        Timber.plant(HyperlinkedDebugTree())
    }

    private fun initRxErrorHandler() {
        //to avoid crash when undelivered exception occurs in rxjava
        RxJavaPlugins.setErrorHandler { e ->
            if (e is UndeliverableException) {
                // Merely log undeliverable exceptions
                Timber.e(e)
            } else {
                // Forward all others to current thread's uncaught exception handler
                Thread.currentThread().also { thread ->
                    thread.uncaughtExceptionHandler?.uncaughtException(thread, e)
                }
            }
        }
    }

}