package ru.tinkoff.favouritepersons.tests

import androidx.test.core.app.ActivityScenario
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
import ru.tinkoff.favouritepersons.PersonItemWithoutSurname
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.StudentDetailsScreen
import ru.tinkoff.favouritepersons.screens.StudentsListScreen
import ru.tinkoff.favouritepersons.screens.StudentsListScreen.listSize

class AddWithoutSurnameTest: TestCase() {

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

            //заполняем все поля ввода кроме поля фамилия и пытаемся добавить студента
            val personItemWithoutSurname = PersonItemWithoutSurname()
            editFieldsAndSubmit(personItemWithoutSurname)

            //проверяем, что мы остались на том же экране
            checkAddScreenIsOpened()
            //проверяем, что отображается сообщение о некорректном заполнении поля ввода
            checkSurnameFieldError()
        }
        device.uiDevice.pressBack()
        StudentsListScreen {
            //проверяем, что количество студентов в списке не увеличилось
            checkListSize(listSizeBeforeAdd)
        }
    }
}