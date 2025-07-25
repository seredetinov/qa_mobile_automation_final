package ru.tinkoff.favouritepersons.tests

import androidx.test.core.app.ActivityScenario
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.StudentDetailsScreen
import ru.tinkoff.favouritepersons.screens.StudentsListScreen

class EditWithoutNameTest: TestCase() {

    @Test
    fun editWithoutNameTest() = run{
        ActivityScenario.launch(MainActivity::class.java)

        //dыполняем предусловие о том, что должен быть минимум один студент
        StudentsListScreen {
            clickAddMenu()
            clickAddPersonManually()
        }
        val validName = "Тимур"
        val surname = "Середетинов"
        val gender = "М"
        val birthdate = "2002-12-12"
        val email = "seredetinofff@gmail.com"
        val phone = "+79992849729"
        val address = "Санкт-Петербург"
        val image = "https:"
        val score = "77"
        StudentDetailsScreen {
            editName(validName)
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
            openStudentCard(validName,surname,gender,email,phone,address,score)
        }

        StudentDetailsScreen {
            //очищаем поле имя и пытаемся сохранить
            clearNameFieldText()
            clickSubmit()
            //проверяем, что сохранение редактирования не осуществилось и мы остались на том же экране
            checkEditScreenIsOpened()
            //проверяем, что отображается сообщение о некорректном заполнении поля ввода
            checkNameFieldError()
        }
        //заново открываем ту же карточку студента и проверяем, что значение поля ввода имя не изменилось
        device.uiDevice.pressBack()
        StudentsListScreen {
            openStudentCard(validName,surname,gender,email,phone,address,score)
        }
        StudentDetailsScreen {
           checkNameFieldText(validName)
        }

    }
}