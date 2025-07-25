package ru.tinkoff.favouritepersons.tests

import androidx.test.core.app.ActivityScenario
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.tinkoff.favouritepersons.Prefs
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.StudentsListScreen
import ru.tinkoff.favouritepersons.CommonFunc.readJsonFileFromAssets

class AddFromCloudTest: TestCase() {

    @get:Rule
    val wireMockRule = WireMockRule(5000)

    @Before
    fun setup(){
        Prefs.changeAppUrl()
    }

    @Test
    fun addFromCloudTest() = run {
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
            val listSizeBeforeAdd = listSize() //количество студентов перед добавлением
            clickAddMenu()
            clickAddPersonFromCloud()
            checkListSize(listSizeBeforeAdd+1) //проверяем, что число студентов увеличилось
        }

        verify(getRequestedFor(
                urlEqualTo("/api/"))
        )

    }

    @After
    fun tearDown() {
        Prefs.clear()
    }
}