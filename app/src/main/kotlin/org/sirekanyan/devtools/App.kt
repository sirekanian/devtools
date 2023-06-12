package org.sirekanyan.devtools

import android.app.Application
import android.content.Context

val Context.app: App
    get() = applicationContext as App

class App : Application() {
    val notifications by lazy { Notifications(this) }
}