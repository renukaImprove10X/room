package com.improve10x.roompractice.practiice2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactDialog(
    state : ContactState2,
    onEvent: (ContactEvent2) -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {onEvent(ContactEvent2.HideDialog)},
        title = { Text(text = "Add Contact")},
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                TextField(
                    value = state.firstName,
                    onValueChange = {
                        onEvent(ContactEvent2.SetFirstName(it))
                    },
                    placeholder = {
                        Text(text = "First Name")
                    }
                )
                TextField(
                    value = state.lastName,
                    onValueChange = {
                        onEvent(ContactEvent2.SetLastName(it))
                    },
                    placeholder = {
                        Text(text = "Last Name")
                    }
                )
                TextField(
                    value = state.phoneNumber,
                    onValueChange = {
                        onEvent(ContactEvent2.SetPhoneNumber(it))
                    },
                    placeholder = {
                        Text(text = "Phone Number")
                    }
                )
                TextField(
                    value = state.emailId,
                    onValueChange = {
                        onEvent(ContactEvent2.SetEmailId(it))
                    },
                    placeholder = {
                        Text(text = "Email Id")
                    }
                )
            }
        },
        confirmButton = {
           Box(
               modifier = Modifier.fillMaxWidth(),
               contentAlignment = Alignment.CenterEnd
           ){
               Button(onClick = {
                   onEvent(ContactEvent2.Save2Contact)
               }){
                   Text(text = "Save")
               }
           }
        }
    )
}