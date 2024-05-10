package org.sirekanyan.devtools

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.sirekanyan.devtools.Profile.Companion.DefaultProfile

class ResetReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        try {
            Settings(context.contentResolver).setProfile(DefaultProfile)
        } catch (exception: Exception) {
            Notifications(context.app).show()
            throw exception
        }
    }
}
