package org.sirekanyan.devtools

import android.os.Bundle
import android.provider.Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KMutableProperty0

class MainActivity : AppCompatActivity() {

    private val settings by lazy { Settings(contentResolver) }
    private val notifications by lazy { app.notifications }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            scroll {
                addLinear {
                    orientation = LinearLayout.VERTICAL
                    addLinear {
                        addText("dev") {
                            notifications.show()
                            settings.init(adb = 1, font = 1.15f, screen = 120, stayAwake = 7)
                            recreate()
                        }
                        addText("reset") {
                            notifications.hide()
                            settings.init()
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
                    addSwitcher(settings::windowAnimation, 1f, 2f, 5f)
                    addSwitcher(settings::transitionAnimation, 1f, 2f, 5f)
                    addSwitcher(settings::animatorDuration, 1f, 2f, 5f)
                    addSwitcher(settings::stayAwake, 0, 7)
                }
            }
        )
        if (savedInstanceState == null) {
            notifications.show()
        }
    }

    private fun <T> LinearLayout.addSwitcher(property: KMutableProperty0<T>, vararg values: T) {
        val currentValue = property.get()
        addLinear {
            orientation = LinearLayout.HORIZONTAL
            values.forEach { value ->
                val text = "${property.name} $value"
                addText(if (value == currentValue) "[$text]" else text) {
                    notifications.show()
                    property.set(value)
                    recreate()
                }
            }
        }
    }
}
