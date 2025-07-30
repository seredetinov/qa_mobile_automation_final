package ru.tinkoff.favouritepersons.tests

import androidx.test.core.app.ActivityScenario
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
import ru.tinkoff.favouritepersons.PersonItem
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.StudentDetailsScreen
import ru.tinkoff.favouritepersons.screens.StudentDetailsScreen.checkAddScreenIsOpened
import ru.tinkoff.favouritepersons.screens.StudentsListScreen
import ru.tinkoff.favouritepersons.screens.StudentsListScreen.listSize

class AddDuplicateTest: TestCase() {

    @Test
    fun addDuplicateTest() = run {
        ActivityScenario.launch(MainActivity::class.java)

        for (i in 1..2) {
            StudentsListScreen {
                if (i==1) clickAddMenu()
                clickAddPersonManually()
            }
            val personItem = PersonItem()
            StudentDetailsScreen {
                editFieldsAndSubmit(personItem)
            }

            var listSizeBeforeAddDuplicate = -1 //количество студентов в списке перед добавлением дубликата
            //когда добавляем первый раз
            if (i==1){
                //количество студентов после добавления валидного студента
                listSizeBeforeAddDuplicate = listSize()
            }
            //когда добавляем дубликат
            else {
                //проверяем, что добавление не осуществилось и мы остались на том же экране
                checkAddScreenIsOpened()
                device.uiDevice.pressBack()
                StudentsListScreen {
                    //проверяем, что количество студентов в списке не увеличилось
                    checkListSize(listSizeBeforeAddDuplicate)
                }
            }
        }

    }

}