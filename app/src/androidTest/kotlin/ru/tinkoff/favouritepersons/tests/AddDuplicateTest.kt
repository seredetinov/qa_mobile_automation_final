package ru.tinkoff.favouritepersons.tests

import androidx.test.core.app.ActivityScenario
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
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
            val name = "Дмитрий"
            val surname = "Кирпа"
            val gender = "М"
            val birthdate = "2002-10-10"
            val email = "seredetinoff@gmail.com"
            val phone = "+79992849729"
            val address = "Великий Новгород"
            val image = "https:"
            val score = "76"
            StudentDetailsScreen {
                editName(name)
                editSurname(surname)
                editGender(gender)
                editBirthdate(birthdate)
                editEmail(email)
                editPhone(phone)
                editAddress(address)
                editImage(image)
                editScore(score)
                clickSubmit()
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