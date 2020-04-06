package com.sirekanyan.devtools

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.setPadding

fun Context.scroll(configure: ScrollView.() -> Unit) =
    ScrollView(this).also(configure)

fun Context.linear(configure: LinearLayout.() -> Unit) =
    LinearLayout(this).also {
        it.gravity = Gravity.CENTER
        it.configure()
    }

fun Context.text(value: String, onClick: () -> Unit = {}) =
    TextView(this).also {
        it.text = value
        it.setPadding(48)
        it.setOnClickListener { onClick() }
    }

fun Context.startIntent(action: String) {
    try {
        startActivity(Intent(action))
    } catch (exception: Exception) {
        Log.e("devtools", "cannot start: $action", exception)
        Toast.makeText(this, "cannot start: $action", Toast.LENGTH_LONG).show()
    }
}