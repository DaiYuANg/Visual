package org.visual.shared

import java.util.prefs.Preferences

class PreferencesWrapper(
    private val preferences: Preferences,
) {
  fun <T> put(key: String, value: T) {
    when (value) {
      is String -> {
        preferences.put(key, value)
      }
      is Double -> {
        preferences.putDouble(key, value)
      }
      is Float -> {
        preferences.putFloat(key, value)
      }
      is Int -> {
        preferences.putInt(key, value)
      }
      is Boolean -> {
        preferences.putBoolean(key, value)
      }
      is ByteArray -> {
        preferences.putByteArray(key, value)
      }
    }
  }

  fun <T> get(key: String, value: T): T? {
    return when (value) {
      is String -> {
        preferences.get(key, value) as T
      }
      is Double -> {
        preferences.getDouble(key, value) as T
      }
      is Float -> {
        preferences.getFloat(key, value) as T
      }
      is Int -> {
        preferences.getInt(key, value) as T
      }
      is Boolean -> {
        preferences.getBoolean(key, value) as T
      }
      is ByteArray -> {
        preferences.getByteArray(key, value) as T
      }
      else -> {
        null
      }
    }
  }

  fun flush() {
    preferences.flush()
  }
}
