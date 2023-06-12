package org.sirekanyan.devtools

import android.content.ContentResolver
import android.provider.Settings.Global
import android.provider.Settings.System

class Settings(val resolver: ContentResolver) {

    var adb: Int by GlobalSetting(Global.ADB_ENABLED)
    var dka: Int by GlobalSetting(Global.ALWAYS_FINISH_ACTIVITIES)
    var font: Float by SystemSetting(System.FONT_SCALE)
    var screen: Long by SystemSetting(System.SCREEN_OFF_TIMEOUT, multiplier = 1000)
    var rotation: Int by SystemSetting(System.USER_ROTATION)

}