package com.improve10x.roompractice.practiice2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactViewModel2(
    private val dao: ContactDao2
) : ViewModel() {

    private val _sortType2 = MutableStateFlow(SortType2.FIRST_NAME)
    private val _state = MutableStateFlow(ContactState2())
    private val _contacts = _sortType2
        .flatMapLatest { sortType ->
            when (sortType) {
                SortType2.FIRST_NAME -> {
                    dao.getContactsOrderByFirstNameDesc()
                }

                SortType2.EMAIL_ID -> {
                    dao.getContactsOrderByEmailIdAsc()
                }

                SortType2.LAST_NAME -> {
                    dao.getContactsOrderByLastNameDesc()
                }

                SortType2.PHONE_NUMBER -> {
                    dao.getContactsOrderByPhoneNumberDesc()
                }
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    val state = combine(_state, _sortType2, _contacts) { state, sortType, contacts ->
        state.copy(
            contacts = contacts,
            sortType2 = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactState2())

    fun onEvent(event: ContactEvent2){
        when(event){
            is ContactEvent2.Delete2Contact -> {
                viewModelScope.launch {
                    dao.deleteContact(event.contact)
                }
            }

            ContactEvent2.HideDialog -> {
                _state.update {
                    it.copy(
                        isAddingContact = false
                    )
                }
            }

            ContactEvent2.Save2Contact -> {
                val firstName = state.value.firstName
                val lastName = state.value.lastName
                val phoneNumber = state.value.phoneNumber
                val emailId = state.value.emailId
                if(firstName.isBlank() || lastName.isBlank() || phoneNumber.isBlank() || emailId.isBlank()){
                    return
                }
                val contact = Contact3(
                    firstName = firstName,
                    lastName = lastName,
                    phoneNumber = phoneNumber,
                    emailId = emailId
                )
                viewModelScope.launch {
                    dao.upsertContact(contact)
                }
                _state.update {
                    it.copy(
                        isAddingContact = false,
                        firstName = "",
                        lastName = "",
                        phoneNumber = "",
                        emailId = ""
                    )
                }
            }

            is ContactEvent2.SetFirstName -> {
                _state.update {
                    it.copy(firstName = event.firstName)
                }
            }
            is ContactEvent2.SetLastName -> {
                _state.update {
                    it.copy(lastName = event.lastName)
                }
            }
            is ContactEvent2.SetPhoneNumber -> {
                _state.update {
                    it.copy(phoneNumber = event.phoneNumber)
                }
            }
            is ContactEvent2.SetEmailId -> {
                _state.update {
                    it.copy(emailId = event.emailId)
                }
            }
            ContactEvent2.ShowDialog -> {
                _state.update {
                    it.copy(
                        isAddingContact = true
                    )
                }
            }
            is ContactEvent2.SortContacts -> {
                _sortType2.value = event.sortType2
            }
        }
    }
}