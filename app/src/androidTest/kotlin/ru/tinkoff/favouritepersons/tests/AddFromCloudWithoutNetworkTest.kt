package ru.tinkoff.favouritepersons.tests

import androidx.test.core.app.ActivityScenario
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import ru.tinkoff.favouritepersons.presentation.activities.MainActivity
import ru.tinkoff.favouritepersons.screens.StudentsListScreen

class AddFromCloudWithoutNetworkTest: TestCase() {

    @Before
    fun setup(){
        device.uiDevice.executeShellCommand("svc wifi disable")
        device.uiDevice.executeShellCommand("svc data disable")
    }

    @Test
    fun addFromCloudWithoutNetworkTest() = run {
        ActivityScenario.launch(MainActivity::class.java)
        StudentsListScreen {
            val listSizeBeforeAdd = listSize() //количество студентов перед добавлением
            clickAddMenu()
            clickAddPersonFromCloud()
            //проверяем, что отображается уведомление об отсутствии Интернета
            checkNoNetworkToggle()
            //проверяем, что число студентов в списке не увеличилось
            checkListSize(listSizeBeforeAdd)
        }
    }

    @After
    fun tearDown(){
        device.uiDevice.executeShellCommand("svc data enable")
    }

}