package ru.tinkoff.favouritepersons.tests

import androidx.test.core.app.ActivityScenario
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
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

        val name = "Тимур"
        val surname = "Середетинов"
        val gender = "М"
        val birthdate = "2002-12-12"
        val email = "seredetinofff@gmail.com"
        val phone = "+79992849729"
        val address = "Санкт-Петербург"
        val image = "https:"
        val score = "77"

        //заполняем поля ввода и нажимаем кнопку Сохранить
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

        StudentsListScreen {
            //проверяем, что число студентов увеличилось
            checkListSize(listSizeBeforeAdd+1)
            //проверяем, что карточка созданного студента добавилась в список
            openStudentCard(name,surname,gender,email,phone,address,score)
        }

    }
}