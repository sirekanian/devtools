package org.sirekanyan.devtools

import android.graphics.Color
import android.os.Bundle
import android.provider.Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import org.sirekanyan.devtools.Profile.Companion.DefaultProfile
import org.sirekanyan.devtools.Profile.Companion.DevelopProfile
import kotlin.reflect.KMutableProperty0

private val UppercaseLetterRegex = Regex("[A-Z]")

class MainActivity : AppCompatActivity() {

    private val settings by lazy { Settings(contentResolver) }
    private val notifications by lazy { app.notifications }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val profile = settings.getProfile()
        setContentView(
            scroll {
                addLinear {
                    orientation = LinearLayout.VERTICAL
                    addLinear {
                        addText("develop", if (profile.isDevelop()) Color.WHITE else Color.BLACK) {
                            notifications.show()
                            settings.setProfile(DevelopProfile)
                            recreate()
                        }
                        addText("default", if (profile.isDefault()) Color.WHITE else Color.BLACK) {
                            notifications.hide()
                            settings.setProfile(DefaultProfile)
                            finish()
                        }
                        onLongClick {
                            startIntent(ACTION_APPLICATION_DEVELOPMENT_SETTINGS)
                        }
                    }
                    addSwitcher(settings::dev, 0, 1)
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
                val key = UppercaseLetterRegex.replace(property.name) { " ${it.value.lowercase()}" }
                addText("$key $value", if (value == currentValue) Color.WHITE else Color.BLACK) {
                    notifications.show()
                    property.set(value)
                    recreate()
                }
            }
        }
    }
}
