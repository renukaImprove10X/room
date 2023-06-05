package com.improve10x.roompractice.practiice2

data class ContactState2(
    val contacts: List<Contact3> = emptyList(),
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val emailId: String = "",
    val isAddingContact: Boolean = false,
    val sortType2: SortType2 = SortType2.FIRST_NAME
)
