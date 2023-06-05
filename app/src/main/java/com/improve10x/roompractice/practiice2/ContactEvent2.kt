package com.improve10x.roompractice.practiice2

sealed interface ContactEvent2{
    object Save2Contact : ContactEvent2
    data class SetFirstName(val firstName : String): ContactEvent2
    data class SetLastName(val lastName : String): ContactEvent2
    data class SetPhoneNumber(val phoneNumber : String): ContactEvent2
    data class SetEmailId(val emailId : String): ContactEvent2
    object ShowDialog : ContactEvent2
    object HideDialog : ContactEvent2
    data class SortContacts(val sortType2 : SortType2) : ContactEvent2
    data class Delete2Contact(val contact: Contact3) : ContactEvent2
}