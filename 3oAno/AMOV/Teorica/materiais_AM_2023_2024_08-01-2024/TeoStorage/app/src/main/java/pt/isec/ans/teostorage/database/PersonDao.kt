package pt.isec.ans.teostorage.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {
    @Query("SELECT * FROM person")
    fun getAll(): List<Person>

    @Query("SELECT * FROM person WHERE id IN (:personIds)")
    fun loadAllByIds(personIds: IntArray): List<Person>

    @Query("SELECT * FROM person WHERE age >= :minAge AND age<= :maxAge LIMIT 1")
    fun findOneByAge(minAge: Int, maxAge: Int): Person?

    @Insert
    fun insertAll(vararg people: Person)

    @Delete
    fun delete(person: Person)
}