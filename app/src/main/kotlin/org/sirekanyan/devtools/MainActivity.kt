package org.sirekanyan.devtools

import android.os.Bundle
import android.provider.Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KMutableProperty0

class MainActivity : AppCompatActivity() {

    private val settings by lazy { Settings(contentResolver) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            scroll {
                addLinear {
                    orientation = LinearLayout.VERTICAL
                    addLinear {
                        addText("dev") {
                            init(adb = 1, font = 1.15f, screen = 120)
                            recreate()
                        }
                        addText("reset") {
                            init(adb = 0, font = 1.0f, screen = 30)
                            finish()
                        }
                        onLongClick {
                            startIntent(ACTION_APPLICATION_DEVELOPMENT_SETTINGS)
                        }
                    }
                    addSwitcher(settings::adb, 0, 1)
                    addSwitcher(settings::dka, 0, 1)
                    addSwitcher(settings::font, 0.85f, 1.0f, 1.15f, 1.3f)
                    addSwitcher(settings::screen, 30, 60, 120)
                }
            }
        )
    }

    private fun init(adb: Int, font: Float, screen: Long) {
        settings.adb = adb
        settings.dka = 0
        settings.font = font
        settings.screen = screen
    }

    private fun <T> LinearLayout.addSwitcher(property: KMutableProperty0<T>, vararg values: T) {
        val currentValue = property.get()
        addLinear {
            orientation = LinearLayout.HORIZONTAL
            values.forEach { value ->
                val text = "${property.name} $value"
                addText(if (value == currentValue) "[$text]" else text) {
                    property.set(value)
                    recreate()
                }
            }
        }
    }

}