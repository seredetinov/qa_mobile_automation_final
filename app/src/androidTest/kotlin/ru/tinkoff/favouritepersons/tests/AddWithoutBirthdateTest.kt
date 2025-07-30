package ru.tinkoff.favouritepersons.tests

import androidx.test.core.app.ActivityScenario
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
import ru.tinkoff.favouritepersons.PersonItemWithoutBirthdate
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.StudentDetailsScreen
import ru.tinkoff.favouritepersons.screens.StudentsListScreen
import ru.tinkoff.favouritepersons.screens.StudentsListScreen.listSize

class AddWithoutBirthdateTest: TestCase() {

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

            //заполняем все поля ввода кроме поля дата рождения и пытаемся добавить студента
            val personItemWithoutBirthdate = PersonItemWithoutBirthdate()
            editFieldsAndSubmit(personItemWithoutBirthdate)

            //проверяем, что мы остались на том же экране
            checkAddScreenIsOpened()
            //проверяем, что отображается сообщение о некорректном заполнении поля ввода
            checkBirthdateFieldError()
        }
        device.uiDevice.pressBack()
        StudentsListScreen {
            //проверяем, что количество студентов в списке не увеличилось
            checkListSize(listSizeBeforeAdd)
        }
    }
}