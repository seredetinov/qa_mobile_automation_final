package ru.tinkoff.favouritepersons.tests

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import ru.tinkoff.favouritepersons.PersonItem
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.StudentsListScreen
import ru.tinkoff.favouritepersons.screens.StudentDetailsScreen

class AddScreenAfterMinimizeTest: TestCase() {

    @Test
    fun addScreenAfterMinimizeTest() = run {
        ActivityScenario.launch(MainActivity::class.java)
        StudentsListScreen {
            clickAddMenu()
            clickAddPersonManually()
        }

        //заполняем поля ввода
        val personItem = PersonItem()
        StudentDetailsScreen {
            editFieldsAndSubmit(personItem, submit = false)
        }

        //сворачиваем приложение
        device.uiDevice.pressHome()

        //возвращаемся в приложение
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val launchIntent = context.packageManager.getLaunchIntentForPackage(PACKAGE_NAME)
        context.startActivity(launchIntent)

        //проверяем, что введенные данные сохранены
        StudentDetailsScreen {
            checkFields(personItem)
        }
    }

    companion object {
        private const val PACKAGE_NAME = "ru.tinkoff.favouritepersons"
    }
}