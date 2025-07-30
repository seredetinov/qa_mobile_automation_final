package ru.tinkoff.favouritepersons

open class PersonItem {
    open val name = "Тимур"
    open val surname = "Середетинов"
    open val gender = "М"
    open val birthdate = "2002-12-12"
    open val email = "seredetinofff@gmail.com"
    open val phone = "+79992849729"
    open val address = "Санкт-Петербург"
    open val image = "https:"
    open val score = "77"
}

class PersonItemWithoutName: PersonItem() {
    override val name = ""
}

class PersonItemWithoutSurname: PersonItem() {
    override val surname = ""
}

class PersonItemWithoutBirthdate: PersonItem() {
    override val birthdate = ""
}

class PersonItemWithoutScore: PersonItem() {
    override val score = ""
}

class PersonItem2: PersonItem() {
    override val name = "Дмитрий"
    override val surname = "Кирпа"
    override val gender = "М"
    override val birthdate = "2002-10-10"
    override val email = "seredetinoff@gmail.com"
    override val phone = "+79992849729"
    override val address = "Великий Новгород"
    override val image = "https:"
    override val score = "76"
}
