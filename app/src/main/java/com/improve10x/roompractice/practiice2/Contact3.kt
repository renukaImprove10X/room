package com.improve10x.roompractice.practiice2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact3(
    val firstName : String,
    val lastName : String,
    val phoneNumber : String,
    val emailId : String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)
