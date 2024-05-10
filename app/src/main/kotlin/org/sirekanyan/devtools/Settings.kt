package org.sirekanyan.devtools

import android.content.ContentResolver
import android.provider.Settings.Global
import android.provider.Settings.System

class Settings(val resolver: ContentResolver) {

    var dev: Int by GlobalSetting(Global.DEVELOPMENT_SETTINGS_ENABLED)
    var adb: Int by GlobalSetting(Global.ADB_ENABLED)
    var dka: Int by GlobalSetting(Global.ALWAYS_FINISH_ACTIVITIES)
    var font: Float by SystemSetting(System.FONT_SCALE)
    var screen: Long by SystemSetting(System.SCREEN_OFF_TIMEOUT, multiplier = 1000)
    @Suppress("unused")
    var rotation: Int by SystemSetting(System.USER_ROTATION)
    var windowAnimation: Float by GlobalSetting(Global.WINDOW_ANIMATION_SCALE)
    var transitionAnimation: Float by GlobalSetting(Global.TRANSITION_ANIMATION_SCALE)
    var animatorDuration: Float by GlobalSetting(Global.ANIMATOR_DURATION_SCALE)
    var stayAwake: Int by GlobalSetting(Global.STAY_ON_WHILE_PLUGGED_IN)

    fun getProfile() =
        Profile(
            dev = dev,
            adb = adb,
            dka = dka,
            font = font,
            screen = screen,
            windowAnimation = windowAnimation,
            transitionAnimation = transitionAnimation,
            animatorDuration = animatorDuration,
            stayAwake = stayAwake,
        )

    fun setProfile(profile: Profile) {
        dev = profile.dev
        adb = profile.adb
        dka = profile.dka
        font = profile.font
        screen = profile.screen
        windowAnimation = profile.windowAnimation
        transitionAnimation = profile.transitionAnimation
        animatorDuration = profile.animatorDuration
        stayAwake = profile.stayAwake
    }
}
