package pt.isec.ans.teostorage.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class Person (
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo(name = "full_name") var name : String,
    var age : Int
)