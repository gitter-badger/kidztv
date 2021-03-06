package com.eltonkola.kidztv


import android.app.Application
import android.content.IntentFilter
import android.util.Log
import androidx.room.Room
import com.eltonkola.kidztv.data.AppManager
import com.eltonkola.kidztv.data.SharedPreferencesManager
import com.eltonkola.kidztv.data.VideoManager
import com.eltonkola.kidztv.data.db.AppDatabase
import com.eltonkola.kidztv.downloadmanager.DownloadReceiver
import com.eltonkola.kidztv.ui.main.MainViewModel
import com.eltonkola.kidztv.ui.main.apps.AppsViewModel
import com.eltonkola.kidztv.ui.main.lock.LockViewModel
import com.eltonkola.kidztv.ui.main.timer.TimerViewModel
import com.eltonkola.kidztv.ui.openVideoPlugin.SampleVideoViewModel
import com.eltonkola.kidztv.ui.settings.appmanager.AppManagerViewModel
import com.eltonkola.kidztv.ui.settings.appmanager.add.AddAppsViewModel
import com.eltonkola.kidztv.ui.settings.pin.PinViewModel
import com.eltonkola.kidztv.ui.settings.videomanager.ViewManagerViewModel
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import timber.log.Timber
import timber.log.Timber.DebugTree

class MainApp : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule))



        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(object : Timber.Tree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    Log.e(tag, message, t)
                }
            })
        }

        val receiver = DownloadReceiver()
        registerReceiver(receiver, IntentFilter("com.eltonkola.kidztv.action.START_DOWNLOAD_SERVICE"))
    }


    val appModule = module {

        single { AppManager(get(), get()) }

        single { SharedPreferencesManager(get()) }

        single { VideoManager(applicationContext) }

        //room db for stats and app whitelist
        single { Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app-data").build() }
//
//        // Simple Presenter Factory, create one each time
//        factory { MySimplePresenter(get()) }

        // MyViewModel ViewModel
        viewModel { PinViewModel(get()) }

        viewModel { AppManagerViewModel(get()) }

        viewModel { AddAppsViewModel(get()) }

        viewModel { AppsViewModel(get()) }

        viewModel { TimerViewModel(get()) }

        viewModel { LockViewModel(get()) }

        viewModel { MainViewModel(get(), get(), get()) }

        viewModel { ViewManagerViewModel(applicationContext, get()) }

        viewModel { SampleVideoViewModel(get()) }


    }


}