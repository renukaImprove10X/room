package com.improve10x.roompractice.practiice2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao2 {

    @Upsert
    suspend fun upsertContact(contact: Contact3)

    @Delete
    suspend fun deleteContact(contact: Contact3)

    @Query("SELECT * FROM contact3 ORDER BY firstName DESC")
    fun getContactsOrderByFirstNameDesc() : Flow<List<Contact3>>

    @Query("SELECT * FROM contact3 ORDER BY lastName DESC")
    fun getContactsOrderByLastNameDesc() : Flow<List<Contact3>>

    @Query("SELECT * FROM contact3 ORDER BY phoneNumber DESC")
    fun getContactsOrderByPhoneNumberDesc() : Flow<List<Contact3>>

    @Query("SELECT * FROM contact3 ORDER BY emailId ASC")
    fun getContactsOrderByEmailIdAsc() : Flow<List<Contact3>>

}