package ru.tinkoff.favouritepersons.tests

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import ru.tinkoff.favouritepersons.CommonFunc.readJsonFileFromAssets
import ru.tinkoff.favouritepersons.Prefs
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.StudentsListScreen
import ru.tinkoff.favouritepersons.screens.StudentDetailsScreen

class EditScreenAfterMinimizeTest: TestCase() {

    @get:Rule
    val wireMockRule = WireMockRule(5000)

    @Before
    fun setup(){
        Prefs.changeAppUrl()
    }

    @Test
    fun addScreenAfterMinimizeTest() = run{
        stubFor(
            get(urlEqualTo("/api/"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type","application/json")
                        .withBody(readJsonFileFromAssets("addFromCloud.json"))
                ))

        ActivityScenario.launch(MainActivity::class.java)
        StudentsListScreen {
            clickAddMenu()
            clickAddPersonFromCloud() //предусловие, что должен быть минимум один студент в списке
            openStudentCardByIndex(0)
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
            checkEditScreenIsOpened()
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

    @After
    fun tearDown() {
        Prefs.clear()
    }

    companion object {
        private const val PACKAGE_NAME = "ru.tinkoff.favouritepersons"
    }
}