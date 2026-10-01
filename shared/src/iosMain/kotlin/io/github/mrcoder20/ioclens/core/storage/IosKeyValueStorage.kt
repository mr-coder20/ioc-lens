package io.github.mrcoder20.ioclens.core.storage

import platform.Foundation.NSUserDefaults

class IosKeyValueStorage : KeyValueStorage {
    private val defaults = NSUserDefaults.standardUserDefaults

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return if (defaults.objectForKey(key) != null) {
            defaults.boolForKey(key)
        } else {
            defaultValue
        }
    }

    override fun setBoolean(key: String, value: Boolean) {
        defaults.setBool(value, forKey = key)
    }

    override fun getString(key: String, defaultValue: String): String {
        return defaults.stringForKey(key) ?: defaultValue
    }

    override fun setString(key: String, value: String) {
        defaults.setObject(value, forKey = key)
    }
}

actual fun createPersistentKeyValueStorage(): KeyValueStorage = IosKeyValueStorage()
