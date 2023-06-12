package org.sirekanyan.devtools

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ResetReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        try {
            Settings(context.contentResolver).init()
        } catch (exception: Exception) {
            Notifications(context.app).show()
            throw exception
        }
    }
}