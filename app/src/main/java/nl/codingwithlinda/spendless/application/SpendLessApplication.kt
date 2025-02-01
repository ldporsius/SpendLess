package nl.codingwithlinda.spendless.application

import android.app.Application
import nl.codingwithlinda.spendless.di.AndroidAppModule
import nl.codingwithlinda.core.di.AppModule

class SpendLessApplication: Application() {

    companion object {
        lateinit var appModule: AppModule
    }
    override fun onCreate() {
        super.onCreate()
        appModule = AndroidAppModule(this)
    }
}