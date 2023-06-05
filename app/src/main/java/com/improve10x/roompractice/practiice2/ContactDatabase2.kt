package com.improve10x.roompractice.practiice2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Contact3::class],
    version = 1
)
abstract class ContactDatabase2 : RoomDatabase() {
    abstract val dao : ContactDao2
}