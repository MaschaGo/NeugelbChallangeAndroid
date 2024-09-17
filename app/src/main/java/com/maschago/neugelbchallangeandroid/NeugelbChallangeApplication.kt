package com.maschago.neugelbchallangeandroid

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import com.maschago.neugelbchallangeandroid.di.provideAppModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class NeugelbChallangeApplication : Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()

        initLogging()
        initDI()
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()
    }

    private fun initDI() {
        startKoin {
            androidContext(this@NeugelbChallangeApplication)
            modules(provideAppModules)
        }
    }

    private fun initLogging() {
        if (BuildConfig.DEBUG && Timber.treeCount == 0) Timber.plant(
            object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String {
                    val className = super.createStackElementTag(element)
                    return "$className ${element.methodName}"
                }
            }
        )
    }
}