package ru.tinkoff.favouritepersons.tests

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.StudentsListScreen
import ru.tinkoff.favouritepersons.screens.StudentDetailsScreen

class AddScreenAfterMinimizeTest: TestCase() {

    @Test
    fun addScreenAfterMinimizeTest() = run {
        ActivityScenario.launch(MainActivity::class.java)
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

        //заполняем поля ввода
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
        }

        //сворачиваем приложение
        device.uiDevice.pressHome()

        //возвращаемся в приложение
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val launchIntent = context.packageManager.getLaunchIntentForPackage(PACKAGE_NAME)
        context.startActivity(launchIntent)

        //проверяем, что введенные данные сохранены
        StudentDetailsScreen {
            checkAddScreenIsOpened()
            checkNameFieldText(name)
            checkSurnameFieldText(surname)
            checkGenderFieldText(gender)
            checkBirthdateFieldText(birthdate)
            checkEmailFieldText(email)
            checkPhoneFieldText(phone)
            checkAddressFieldText(address)
            checkImageFieldText(image)
            checkScoreFieldText(score)
        }
    }

    companion object {
        private const val PACKAGE_NAME = "ru.tinkoff.favouritepersons"
    }
}