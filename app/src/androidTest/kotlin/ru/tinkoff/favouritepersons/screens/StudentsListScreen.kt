package ru.tinkoff.favouritepersons.screens

import android.view.View
import androidx.test.espresso.action.ViewActions
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import ru.tinkoff.favouritepersons.R
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object StudentsListScreen: Screen<StudentsListScreen>() {
    private val studentsList = KRecyclerView(
        builder = {withId(R.id.rv_person_list)},
        itemTypeBuilder = {itemType(::StudentCard)}
    )

    private class StudentCard(matcher: Matcher<View>) : KRecyclerItem<StudentCard>(matcher) {
        val avatar = KImageView {withId(R.id.person_avatar)}
        val name = KTextView {withId(R.id.person_name)}
        val genderAndAge = KTextView {withId(R.id.person_private_info)}
        val email = KTextView {withId(R.id.person_email)}
        val phone = KTextView {withId(R.id.person_phone)}
        val address = KTextView {withId(R.id.person_address)}
        val score = KTextView {withId(R.id.person_rating)}
    }

    private val btnAddPerson = KButton {withId(R.id.fab_add_person)}
    private val btnAddPersonManually = KButton {withId(R.id.fab_add_person_manually)}
    private val btnAddPersonFromCloud = KButton {withId(R.id.fab_add_person_by_network)}
    private val noNetworkToggle = KTextView {withText("Internet error! Check your connection")}

    fun openStudentCardByIndex(index: Int){
        studentsList.childWith<StudentCard>{
            withIndex(index) {  }
        } perform {
            click()
        }
    }


    fun deleteFirstStudentCard(){
        studentsList.firstChild<StudentCard> {
            view.perform(ViewActions.swipeLeft())
        }
    }

    fun scrollToEnd(){
        studentsList.scrollToEnd()
        studentsList.childAt<StudentCard>(listSize()-1){
            isVisible()
        }
    }

    fun openStudentCard(name: String, surname: String, gender: String, email: String, phone: String, address: String, score: String){
        val genderFormatted = if (gender=="М") "Male" else "Female"
        studentsList.childWith<StudentCard>{
            withDescendant { containsText(name) }
            withDescendant { containsText(surname) }
            withDescendant { containsText(genderFormatted) }
            withDescendant { containsText(email) }
            withDescendant { containsText(phone) }
            withDescendant { containsText(address) }
            withDescendant { containsText(score) }
        } perform {
            click()
        }
    }

    fun listSize(): Int{
        return studentsList.getSize()
    }

    fun checkListSize(size: Int){
        studentsList.hasSize(size)
    }

    fun clickAddMenu(){
        btnAddPerson.click()
    }
    fun clickAddPersonManually() {
        btnAddPersonManually.click()
    }

    fun clickAddPersonFromCloud() {
        btnAddPersonFromCloud.click()
    }

    fun checkNoNetworkToggle(){
        noNetworkToggle.isDisplayed()
    }

}