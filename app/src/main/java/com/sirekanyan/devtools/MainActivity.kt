package com.sirekanyan.devtools

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KMutableProperty0

class MainActivity : AppCompatActivity() {

    private val settings by lazy { Settings(contentResolver) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            scroll {
                addView(linear {
                    orientation = LinearLayout.VERTICAL
                    addView(linear {
                        addView(text("dev") {
                            init(adb = 1, font = 1.15f, screen = 120)
                            recreate()
                        })
                        addView(text("reset") {
                            init(adb = 0, font = 1.0f, screen = 30)
                            finish()
                        })
                    })
                    switcher(settings::adb, 0, 1)
                    switcher(settings::dka, 0, 1)
                    switcher(settings::font, 0.85f, 1.0f, 1.15f, 1.3f)
                    switcher(settings::screen, 30, 60, 120)
                })
            }
        )
    }

    private fun init(adb: Int, font: Float, screen: Long) {
        settings.adb = adb
        settings.dka = 0
        settings.font = font
        settings.screen = screen
    }

    private fun <T> LinearLayout.switcher(property: KMutableProperty0<T>, vararg values: T) {
        val currentValue = property.get()
        addView(linear {
            orientation = LinearLayout.HORIZONTAL
            values.forEach { value ->
                val text = "${property.name} $value"
                addView(text(if (value == currentValue) "[$text]" else text) {
                    property.set(value)
                    recreate()
                })
            }
        })
    }

}