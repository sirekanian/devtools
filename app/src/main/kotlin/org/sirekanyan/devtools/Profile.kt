package org.sirekanyan.devtools

data class Profile(
    val dev: Int,
    val adb: Int,
    val dka: Int,
    val font: Float,
    val screen: Long,
    val windowAnimation: Float,
    val transitionAnimation: Float,
    val animatorDuration: Float,
    val stayAwake: Int,
) {

    fun isDefault(): Boolean =
        this == DefaultProfile

    fun isDevelop(): Boolean =
        this == DevelopProfile

    companion object {
        val DefaultProfile =
            Profile(
                dev = 0,
                adb = 0,
                dka = 0,
                font = 1.0f,
                screen = 30,
                windowAnimation = 1f,
                transitionAnimation = 1f,
                animatorDuration = 1f,
                stayAwake = 0,
            )
        val DevelopProfile =
            Profile(
                dev = 1,
                adb = 1,
                dka = 0,
                font = 1.15f,
                screen = 120,
                windowAnimation = 1f,
                transitionAnimation = 1f,
                animatorDuration = 1f,
                stayAwake = 7,
            )
    }
}
