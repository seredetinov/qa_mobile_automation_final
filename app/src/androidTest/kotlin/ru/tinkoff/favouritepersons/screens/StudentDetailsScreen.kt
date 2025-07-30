package ru.tinkoff.favouritepersons.screens

import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.edit.KTextInputLayout
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import ru.tinkoff.favouritepersons.PersonItem
import ru.tinkoff.favouritepersons.R

object StudentDetailsScreen: Screen<StudentDetailsScreen>() {
    private val title = KTextView {withId(R.id.tw_person_screen_title)}
    private val nameField = KEditText {withId(R.id.et_name)}
    private val surnameField = KEditText {withId(R.id.et_surname)}
    private val genderField = KEditText {withId(R.id.et_gender)}
    private val birthdateField = KEditText {withId(R.id.et_birthdate)}
    private val emailField = KEditText {withId(R.id.et_email)}
    private val phoneField = KEditText {withId(R.id.et_phone)}
    private val addressField = KEditText {withId(R.id.et_address)}
    private val imageField = KEditText {withId(R.id.et_image)}
    private val scoreField = KEditText {withId(R.id.et_score)}
    private val submitButton = KButton {withId(R.id.submit_button)}
    private val tilName = KTextInputLayout {withId(R.id.til_name)}
    private val tilSurname = KTextInputLayout {withId(R.id.til_surname)}
    private val tilBirthdate = KTextInputLayout {withId(R.id.til_birthdate)}
    private val tilScore = KTextInputLayout {withId(R.id.til_score)}

    fun checkNameFieldError(){
        tilName.hasError("Поле должно быть заполнено!")
    }

    fun checkSurnameFieldError(){
        tilSurname.hasError("Поле должно быть заполнено!")
    }

    fun checkBirthdateFieldError(){
        tilBirthdate.hasError("Поле должно быть заполнено в формате 1990-12-31")
    }

    fun checkScoreFieldError(){
        tilScore.hasError("Поле должно быть заполнено двузначным числом")
    }

    fun editFieldsAndSubmit(personItem: PersonItem, submit: Boolean = true){
        nameField.replaceText(personItem.name)
        surnameField.replaceText(personItem.surname)
        genderField.replaceText(personItem.gender)
        birthdateField.replaceText(personItem.birthdate)
        emailField.replaceText(personItem.email)
        phoneField.replaceText(personItem.phone)
        addressField.replaceText(personItem.address)
        imageField.replaceText(personItem.image)
        scoreField.replaceText(personItem.score)
        if (submit==true) submitButton.click()
    }


    fun editName(name: String){
        nameField.replaceText(name)
    }

    fun editSurname(surname: String){
        surnameField.replaceText(surname)
    }

    fun editGender(gender: String){
        genderField.replaceText(gender)
    }

    fun editBirthdate(birthdate: String){
        birthdateField.replaceText(birthdate)
    }

    fun editEmail(email: String){
        emailField.replaceText(email)
    }

    fun editPhone(phone: String){
        phoneField.replaceText(phone)
    }

    fun editAddress(address: String){
        addressField.replaceText(address)
    }

    fun editImage(imageLink: String){
        imageField.replaceText(imageLink)
    }

    fun editScore(score: String){
        scoreField.replaceText(score)
    }

    fun checkFields(personItem: PersonItem) {
        nameField.hasText(personItem.name)
        surnameField.hasText(personItem.surname)
        genderField.hasText(personItem.gender)
        birthdateField.hasText(personItem.birthdate)
        emailField.hasText(personItem.email)
        phoneField.hasText(personItem.phone)
        addressField.hasText(personItem.address)
        imageField.hasText(personItem.image)
        scoreField.hasText(personItem.score)
    }

    fun checkNameFieldText(text: String){
        nameField.hasText(text)
    }

    fun checkSurnameFieldText(text: String){
        surnameField.hasText(text)
    }

    fun checkGenderFieldText(text: String){
        genderField.hasText(text)
    }

    fun checkBirthdateFieldText(text: String){
        birthdateField.hasText(text)
    }

    fun checkEmailFieldText(text: String){
        emailField.hasText(text)
    }

    fun checkPhoneFieldText(text: String){
        phoneField.hasText(text)
    }

    fun checkAddressFieldText(text: String){
        addressField.hasText(text)
    }

    fun checkImageFieldText(text: String){
        imageField.hasText(text)
    }

    fun checkScoreFieldText(text: String){
        scoreField.hasText(text)
    }

    fun clickSubmit(){
        submitButton.click()
    }

    fun checkAddScreenIsOpened(){
        title.isDisplayed()
        title.hasText("Добавление пользователя")
    }

    fun checkEditScreenIsOpened(){
        title.isDisplayed()
        title.hasText("Редактирование пользователя")
    }

    fun clearNameFieldText(){
        nameField.clearText()
    }

    fun clearSurnameFieldText(){
        surnameField.clearText()
    }

    fun clearBirthdateFieldText(){
        birthdateField.clearText()
    }

    fun clearScoreFieldText(){
        scoreField.clearText()
    }
}