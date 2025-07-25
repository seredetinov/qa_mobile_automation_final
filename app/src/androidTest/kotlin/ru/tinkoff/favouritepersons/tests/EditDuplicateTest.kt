package ru.tinkoff.favouritepersons.tests

import androidx.test.core.app.ActivityScenario
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
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
        val name = "Тимур"
        val surname = "Середетинов"
        val gender = "М"
        val birthdate = "2002-12-12"
        val email = "seredetinofff@gmail.com"
        val phone = "+79992849729"
        val address = "Санкт-Петербург"
        val image = "https:"
        val score = "77"
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

        //создание второго студенте, которого мы сделаем дубликатом при редактировании
        StudentsListScreen {
            clickAddPersonManually()
        }
        val name2 = "Дмитрий"
        val surname2 = "Кирпа"
        val gender2 = "М"
        val birthdate2 = "2002-10-10"
        val email2 = "seredetinoff@gmail.com"
        val phone2 = "+79992849729"
        val address2 = "Великий Новгород"
        val image2 = "https:"
        val score2 = "76"
        StudentDetailsScreen {
            editName(name2)
            editSurname(surname2)
            editGender(gender2)
            editBirthdate(birthdate2)
            editEmail(email2)
            editPhone(phone2)
            editAddress(address2)
            editImage(image2)
            editScore(score2)
            clickSubmit()
        }

        //открываем карточку второго студента и заполняем все поля ввода значениями первого студента
        StudentsListScreen {
            openStudentCard(name2,surname2,gender2,email2,phone2,address2,score2)
        }
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

            //проверяем, что сохранение редактирования не осуществилось и мы остались на том же экране
            checkEditScreenIsOpened()
        }
        device.uiDevice.pressBack()
        //проверяем, что карточка второго студента не обновилась в соответствие со значениями первого студента и её можно найти и открыть
        StudentsListScreen {
            openStudentCard(name2,surname2,gender2,email2,phone2,address2,score2)
        }
    }

}