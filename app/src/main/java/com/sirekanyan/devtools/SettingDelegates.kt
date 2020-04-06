package com.sirekanyan.devtools

import android.provider.Settings.Global
import android.provider.Settings.System
import android.util.Log
import com.sirekanyan.devtools.exceptions.UnknownPropertyTypeError
import kotlin.reflect.KProperty

class GlobalSetting<T>(private val key: String) {

    @Suppress("UNCHECKED_CAST")
    operator fun getValue(thisRef: Settings, property: KProperty<*>): T =
        when (property.returnType.classifier) {
            Int::class -> Global.getInt(thisRef.resolver, key)
            else -> throw UnknownPropertyTypeError(property)
        } as T

    operator fun setValue(thisRef: Settings, property: KProperty<*>, value: T): Boolean =
        try {
            when (property.returnType.classifier) {
                Int::class -> Global.putInt(thisRef.resolver, key, value as Int)
                else -> throw UnknownPropertyTypeError(property)
            }
        } catch (ex: IllegalArgumentException) {
            Log.e("devtools", "Can't set global value", ex)
            false
        }

}

class SystemSetting<T>(private val key: String, private val multiplier: Long = 1) {

    @Suppress("UNCHECKED_CAST")
    operator fun getValue(thisRef: Settings, property: KProperty<*>): T =
        when (property.returnType.classifier) {
            Float::class -> System.getFloat(thisRef.resolver, key)
            Long::class -> System.getLong(thisRef.resolver, key) / multiplier
            Int::class -> System.getInt(thisRef.resolver, key)
            else -> throw UnknownPropertyTypeError(property)
        } as T

    operator fun setValue(thisRef: Settings, property: KProperty<*>, value: T): Boolean =
        try {
            when (property.returnType.classifier) {
                Float::class -> System.putFloat(thisRef.resolver, key, value as Float)
                Long::class -> System.putLong(thisRef.resolver, key, value as Long * multiplier)
                Int::class -> System.putInt(thisRef.resolver, key, value as Int)
                else -> throw UnknownPropertyTypeError(property)
            }
        } catch (ex: IllegalArgumentException) {
            Log.e("devtools", "Can't set system value", ex)
            false
        }

}