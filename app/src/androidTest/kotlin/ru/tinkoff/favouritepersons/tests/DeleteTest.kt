package ru.tinkoff.favouritepersons.tests

import androidx.test.core.app.ActivityScenario
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
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
import ru.tinkoff.favouritepersons.Prefs
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.StudentsListScreen

class DeleteTest: TestCase() {
    @get:Rule
    val wireMockRule = WireMockRule(5000)

    @Before
    fun setup(){
        Prefs.changeAppUrl()
    }

    @Test
    fun deleteTest() = run {
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
            val listSizeBeforeDelete = listSize()
            deleteFirstStudentCard()
            //проверяем, что число студентов в списке уменьшилось
            checkListSize(listSizeBeforeDelete-1)
        }
    }

    @After
    fun tearDown() {
        Prefs.clear()
    }
}