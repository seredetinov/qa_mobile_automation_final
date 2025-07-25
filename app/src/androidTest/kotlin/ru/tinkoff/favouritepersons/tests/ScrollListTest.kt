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

class ScrollListTest: TestCase() {

    @get:Rule
    val wireMockRule = WireMockRule(5000)

    @Before
    fun setup(){
        Prefs.changeAppUrl()
    }

    @Test
    fun scrollListTest(){
        stubFor(
            get(urlEqualTo("/api/"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type","application/json")
                        .withBody(readJsonFileFromAssets("addFromCloud.json"))
                ))

        ActivityScenario.launch(MainActivity::class.java)
        StudentsListScreen{
            clickAddMenu()
            //предусловие, что должно быть такое количество студентов, что не все карточки помещаются на экран
            for (i in 1..6){
                clickAddPersonFromCloud()
            }
            //проверяем, что список успешно прокручивается вниз до конца
            scrollToEnd()
        }
    }

    @After
    fun tearDown() {
        Prefs.clear()
    }

}