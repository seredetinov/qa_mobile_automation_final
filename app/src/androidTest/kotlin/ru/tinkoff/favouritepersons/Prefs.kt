package ru.tinkoff.favouritepersons

import androidx.test.platform.app.InstrumentationRegistry
import android.content.Context

object Prefs {
    private val prefs = InstrumentationRegistry
        .getInstrumentation().targetContext.getSharedPreferences("demo_url",Context.MODE_PRIVATE)

    fun changeAppUrl(url: String = "http://localhost:5000") {
        prefs
            .edit()
            .putString("url", url)
            .apply()
    }

    fun clear() {
        prefs
            .edit()
            .clear()
            .apply()
    }
}