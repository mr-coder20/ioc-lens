package io.github.mrcoder20.ioclens.core.storage

import androidx.compose.runtime.mutableStateMapOf

/**
 * Key-Value Storage interface for persistent settings in Common Kotlin.
 */
interface KeyValueStorage {
    fun getBoolean(key: String, defaultValue: Boolean): Boolean
    fun setBoolean(key: String, value: Boolean)
    fun getString(key: String, defaultValue: String): String
    fun setString(key: String, value: String)
}

/**
 * Compose State backed InMemory KeyValueStorage.
 */
class InMemoryKeyValueStorage : KeyValueStorage {
    private val booleanStorage = mutableStateMapOf<String, Boolean>()
    private val stringStorage = mutableStateMapOf<String, String>()

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return booleanStorage[key] ?: defaultValue
    }

    override fun setBoolean(key: String, value: Boolean) {
        booleanStorage[key] = value
    }

    override fun getString(key: String, defaultValue: String): String {
        return stringStorage[key] ?: defaultValue
    }

    override fun setString(key: String, value: String) {
        stringStorage[key] = value
    }
}
