package com.jmuto.forcus

import android.app.Application
import com.jmuto.forcus.data.AppDataContainer

// アプリ開始のイベントハンドラ
// AndroidManifestのapplicationでこのクラスを指定する
class MainApplication : Application() {
    lateinit var container: AppDataContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}