package org.sirekanyan.devtools

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.setPadding

fun Context.scroll(configure: ScrollView.() -> Unit) =
    ScrollView(this).also(configure)

fun ViewGroup.addLinear(configure: LinearLayout.() -> Unit) =
    addView(context.linear(configure))

private fun Context.linear(configure: LinearLayout.() -> Unit) =
    LinearLayout(this).also {
        it.gravity = Gravity.CENTER
        it.configure()
    }

fun ViewGroup.addText(value: String, onClick: () -> Unit = {}) =
    addView(context.text(value, onClick))

private fun Context.text(value: String, onClick: () -> Unit = {}) =
    TextView(this).also {
        it.text = value
        it.setPadding(48)
        it.setOnClickListener { onClick() }
        it.gravity = Gravity.CENTER
        it.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1f,
        )
    }

fun View.onLongClick(listener: (View) -> Unit) {
    setOnLongClickListener { view ->
        listener(view)
        true
    }
}

fun Context.startIntent(action: String) {
    try {
        startActivity(Intent(action))
    } catch (exception: Exception) {
        Log.e("devtools", "cannot start: $action", exception)
        Toast.makeText(this, "cannot start: $action", Toast.LENGTH_LONG).show()
    }
}
