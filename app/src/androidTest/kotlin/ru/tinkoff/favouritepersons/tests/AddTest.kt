package ru.tinkoff.favouritepersons.tests

import androidx.test.core.app.ActivityScenario
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
import ru.tinkoff.favouritepersons.PersonItem
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.StudentDetailsScreen
import ru.tinkoff.favouritepersons.screens.StudentsListScreen

class AddTest: TestCase() {

    @Test
    fun addTest() = run{
        ActivityScenario.launch(MainActivity::class.java)
        var listSizeBeforeAdd = -1
        StudentsListScreen {
            listSizeBeforeAdd = listSize() //количество студентов перед добавлением
            clickAddMenu()
            clickAddPersonManually()
        }

        //заполняем поля ввода и нажимаем кнопку Сохранить
        val personItem = PersonItem()
        StudentDetailsScreen {
            editFieldsAndSubmit(personItem)
        }

        StudentsListScreen {
            //проверяем, что число студентов увеличилось
            checkListSize(listSizeBeforeAdd+1)
            //проверяем, что карточка созданного студента добавилась в список
            openStudentCard(personItem.name,personItem.surname,personItem.gender,personItem.email,personItem.phone,personItem.address,personItem.score)
        }

    }
}