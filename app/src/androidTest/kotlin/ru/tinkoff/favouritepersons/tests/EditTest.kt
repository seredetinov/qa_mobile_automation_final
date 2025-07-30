package ru.tinkoff.favouritepersons.tests

import androidx.test.core.app.ActivityScenario
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder.okForJson
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.tinkoff.favouritepersons.CommonFunc.readJsonFileFromAssets
import ru.tinkoff.favouritepersons.PersonItem
import ru.tinkoff.favouritepersons.Prefs
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.StudentDetailsScreen
import ru.tinkoff.favouritepersons.screens.StudentsListScreen

class EditTest: TestCase() {

    @get:Rule
    val wireMockRule = WireMockRule(5000)

    @Before
    fun setup(){
        Prefs.changeAppUrl()
    }

    @Test
    fun editTest() = run {
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

        //изменяем значения в полях ввода и нажимаем кнопку Сохранить
        val personItem = PersonItem()
        StudentDetailsScreen {
            editFieldsAndSubmit(personItem)
        }

        //проверяем, что карточка студента обновилась
        StudentsListScreen {
            openStudentCard(personItem.name,personItem.surname,personItem.gender,personItem.email,personItem.phone,personItem.address,personItem.score)
        }

        //проверяем, что отредактированные поля сохранены
        StudentDetailsScreen {
            checkFields(personItem)
        }
    }

    @After
    fun tearDown() {
        Prefs.clear()
    }
}