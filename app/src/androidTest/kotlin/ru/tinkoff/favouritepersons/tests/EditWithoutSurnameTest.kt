package ru.tinkoff.favouritepersons.tests

import androidx.test.core.app.ActivityScenario
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
import ru.tinkoff.favouritepersons.PersonItem
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.StudentDetailsScreen
import ru.tinkoff.favouritepersons.screens.StudentsListScreen

class EditWithoutSurnameTest: TestCase() {

    @Test
    fun editWithoutNameTest() = run{
        ActivityScenario.launch(MainActivity::class.java)

        //Выполняем предусловие о том, что должен быть минимум один студент
        StudentsListScreen {
            clickAddMenu()
            clickAddPersonManually()
        }
        val personItem = PersonItem()
        StudentDetailsScreen {
            editFieldsAndSubmit(personItem)
        }

        StudentsListScreen {
            openStudentCard(personItem.name,personItem.surname,personItem.gender,personItem.email,personItem.phone,personItem.address,personItem.score)
        }

        StudentDetailsScreen {
            //очищаем поле фамилия и пытаемся сохранить
            clearSurnameFieldText()
            clickSubmit()
            //проверяем, что сохранение редактирования не осуществилось и мы остались на том же экране
            checkEditScreenIsOpened()
            //проверяем, что отображается сообщение о некорректном заполнении поля ввода
            checkSurnameFieldError()
        }
        //заново открываем ту же карточку студента и проверяем, что значение поля ввода фамилия не изменилось
        device.uiDevice.pressBack()
        StudentsListScreen {
            openStudentCard(personItem.name,personItem.surname,personItem.gender,personItem.email,personItem.phone,personItem.address,personItem.score)
        }
        StudentDetailsScreen {
           checkSurnameFieldText(personItem.surname)
        }

    }
}