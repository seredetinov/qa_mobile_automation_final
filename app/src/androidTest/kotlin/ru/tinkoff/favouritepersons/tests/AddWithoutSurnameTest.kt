package ru.tinkoff.favouritepersons.tests

import androidx.test.core.app.ActivityScenario
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
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

            val name = "Тимур"
            //val surname = "Середетинов"
            val gender = "М"
            val birthdate = "2002-12-12"
            val email = "seredetinofff@gmail.com"
            val phone = "+79992849729"
            val address = "Санкт-Петербург"
            val image = "https:"
            val score = "77"

            //заполняем все поля ввода кроме поля фамилия и пытаемся добавить студента
            editName(name)
            //editSurname(surname)
            editGender(gender)
            editBirthdate(birthdate)
            editEmail(email)
            editPhone(phone)
            editAddress(address)
            editImage(image)
            editScore(score)
            clickSubmit()

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