package ru.tinkoff.favouritepersons

import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets

object CommonFunc {

    fun readJsonFileFromAssets(fileName: String): String? {
        val context = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().targetContext
        return try {
            val inputStream: InputStream = context.assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, StandardCharsets.UTF_8)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            null
        }
    }
}