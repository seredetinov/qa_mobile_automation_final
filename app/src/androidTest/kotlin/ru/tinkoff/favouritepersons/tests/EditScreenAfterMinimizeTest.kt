package ru.tinkoff.favouritepersons.tests

import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder.okForJson
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import ru.tinkoff.favouritepersons.CommonFunc.readJsonFileFromAssets
import ru.tinkoff.favouritepersons.PersonItem
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
                .willReturn(okForJson(readJsonFileFromAssets("addFromCloud.json")))
        )

        ActivityScenario.launch(MainActivity::class.java)
        StudentsListScreen {
            clickAddMenu()
            clickAddPersonFromCloud() //предусловие, что должен быть минимум один студент в списке
            openStudentCardByIndex(0)
        }

        //заполняем поля ввода
        val personItem = PersonItem()
        StudentDetailsScreen {
            editFieldsAndSubmit(personItem, submit = false)
        }

        //сворачиваем приложение
        device.uiDevice.pressHome()

        //возвращаемся в приложение
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val launchIntent = context.packageManager.getLaunchIntentForPackage(PACKAGE_NAME)
        context.startActivity(launchIntent)

        //проверяем, что введенные данные сохранены
        StudentDetailsScreen {
            checkFields(personItem)
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