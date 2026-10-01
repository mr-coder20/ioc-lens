package io.github.mrcoder20.ioclens.core.storage

import java.io.File
import java.util.Properties

class PersistentFileKeyValueStorage(
    private val storageFile: File
) : KeyValueStorage {

    private val properties = Properties()

    init {
        if (storageFile.exists()) {
            runCatching {
                storageFile.inputStream().use { properties.load(it) }
            }
        }
    }

    private fun save() {
        runCatching {
            storageFile.parentFile?.mkdirs()
            storageFile.outputStream().use { properties.store(it, "IOC Lens Persistent Settings") }
        }
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        val valStr = properties.getProperty(key) ?: return defaultValue
        return valStr.toBooleanStrictOrNull() ?: defaultValue
    }

    override fun setBoolean(key: String, value: Boolean) {
        properties.setProperty(key, value.toString())
        save()
    }

    override fun getString(key: String, defaultValue: String): String {
        return properties.getProperty(key, defaultValue)
    }

    override fun setString(key: String, value: String) {
        properties.setProperty(key, value)
        save()
    }
}

actual fun createPersistentKeyValueStorage(): KeyValueStorage {
    val dir = File(System.getProperty("java.io.tmpdir") ?: ".", "ioclens")
    dir.mkdirs()
    return PersistentFileKeyValueStorage(File(dir, "settings.properties"))
}
