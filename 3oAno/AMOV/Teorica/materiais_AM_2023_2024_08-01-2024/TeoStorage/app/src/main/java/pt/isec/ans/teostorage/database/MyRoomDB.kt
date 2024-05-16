package pt.isec.ans.teostorage.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Person::class), version = 1)
abstract class MyRoomDB : RoomDatabase() {
    abstract fun personDao() : PersonDao
}