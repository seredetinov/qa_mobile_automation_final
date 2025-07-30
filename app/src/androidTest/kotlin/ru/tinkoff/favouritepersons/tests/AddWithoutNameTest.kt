package ru.tinkoff.favouritepersons.tests

import androidx.test.core.app.ActivityScenario
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
import ru.tinkoff.favouritepersons.PersonItemWithoutName
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.StudentDetailsScreen
import ru.tinkoff.favouritepersons.screens.StudentsListScreen
import ru.tinkoff.favouritepersons.screens.StudentsListScreen.listSize

class AddWithoutNameTest: TestCase() {

    @Test
    fun addWithoutNameTest() = run {
        ActivityScenario.launch(MainActivity::class.java)
        val listSizeBeforeAdd = listSize() //количество студентов в списке изначально
        StudentsListScreen {
            clickAddMenu()
            clickAddPersonManually()
        }
        StudentDetailsScreen {
            checkAddScreenIsOpened()

            //заполняем все поля ввода кроме поля имя и пытаемся добавить студента
            val personItemWithoutName = PersonItemWithoutName()
            editFieldsAndSubmit(personItemWithoutName)

            //проверяем, что мы остались на том же экране
            checkAddScreenIsOpened()
            //проверяем, что отображается сообщение о некорректном заполнении поля ввода
            checkNameFieldError()
        }
        device.uiDevice.pressBack()
        StudentsListScreen {
            //проверяем, что количество студентов в списке не увеличилось
            checkListSize(listSizeBeforeAdd)
        }
    }
}