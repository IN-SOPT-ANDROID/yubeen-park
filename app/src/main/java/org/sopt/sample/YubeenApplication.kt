package org.sopt.sample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.sopt.sample.util.SoptDebugTree
import timber.log.Timber

@HiltAndroidApp
class YubeenApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(SoptDebugTree())
        }
    }
}