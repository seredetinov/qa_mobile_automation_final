package ru.tinkoff.favouritepersons.tests

import androidx.test.core.app.ActivityScenario
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
import ru.tinkoff.favouritepersons.PersonItem
import ru.tinkoff.favouritepersons.PersonItem2
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.StudentDetailsScreen
import ru.tinkoff.favouritepersons.screens.StudentsListScreen

class EditDuplicateTest: TestCase() {

    @Test
    fun editTest() = run {
        ActivityScenario.launch(MainActivity::class.java)

        //создание первого студенте
        StudentsListScreen {
            clickAddMenu()
            clickAddPersonManually()
        }
        val personItem = PersonItem()
        StudentDetailsScreen {
            editFieldsAndSubmit(personItem)
        }

        //создание второго студенте, которого мы сделаем дубликатом при редактировании
        StudentsListScreen {
            clickAddPersonManually()
        }
        val personItem2 = PersonItem2()
        StudentDetailsScreen {
            editFieldsAndSubmit(personItem2)
        }

        //открываем карточку второго студента и заполняем все поля ввода значениями первого студента
        StudentsListScreen {
            openStudentCard(personItem2.name,personItem2.surname,personItem2.gender,personItem2.email,personItem2.phone,personItem2.address,personItem2.score)
        }
        StudentDetailsScreen {
            editFieldsAndSubmit(personItem)

            //проверяем, что сохранение редактирования не осуществилось и мы остались на том же экране
            checkEditScreenIsOpened()
        }
        device.uiDevice.pressBack()
        //проверяем, что карточка второго студента не обновилась в соответствие со значениями первого студента и её можно найти и открыть
        StudentsListScreen {
            openStudentCard(personItem2.name,personItem2.surname,personItem2.gender,personItem2.email,personItem2.phone,personItem2.address,personItem2.score)
        }
    }

}