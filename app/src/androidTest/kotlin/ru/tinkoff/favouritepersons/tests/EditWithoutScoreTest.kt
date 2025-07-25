package ru.tinkoff.favouritepersons.tests

import androidx.test.core.app.ActivityScenario
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.StudentDetailsScreen
import ru.tinkoff.favouritepersons.screens.StudentsListScreen

class EditWithoutScoreTest: TestCase() {

    @Test
    fun editWithoutNameTest() = run{
        ActivityScenario.launch(MainActivity::class.java)

        //Выполняем предусловие о том, что должен быть минимум один студент
        StudentsListScreen {
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
        val validScore = "77"
        StudentDetailsScreen {
            editName(name)
            editSurname(surname)
            editGender(gender)
            editBirthdate(birthdate)
            editEmail(email)
            editPhone(phone)
            editAddress(address)
            editImage(image)
            editScore(validScore)
            clickSubmit()
        }

        StudentsListScreen {
            openStudentCard(name,surname,gender,email,phone,address,validScore)
        }

        StudentDetailsScreen {
            //очищаем поле рейтинг и пытаемся сохранить
            clearScoreFieldText()
            clickSubmit()
            //проверяем, что сохранение редактирования не осуществилось и мы остались на том же экране
            checkEditScreenIsOpened()
            //проверяем, что отображается сообщение о некорректном заполнении поля ввода
            checkScoreFieldError()
        }
        //заново открываем ту же карточку студента и проверяем, что значение поля ввода рейтинг не изменилось
        device.uiDevice.pressBack()
        StudentsListScreen {
            openStudentCard(name,surname,gender,email,phone,address,validScore)
        }
        StudentDetailsScreen {
           checkScoreFieldText(validScore)
        }
    }
}