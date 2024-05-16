package pt.isec.ans.teostorage.fragments

import android.view.View
import androidx.room.Room
import pt.isec.ans.teostorage.database.MyRoomDB
import pt.isec.ans.teostorage.database.Person
import pt.isec.ans.teostorage.Utils
import kotlin.concurrent.thread
import kotlin.random.Random

const val ROOM_DATABASE_NAME = "myroomdb.db"

class RoomDBFragment : BaseStorageFragment() {
    override val txtTitle: String
        get() = "Room DB"
    override val txtButton1 : String
        get() = "Insert"
    override val txtButton2 : String
        get() = "Query"

    override fun onButton1(v: View) {
        thread {
            val sb = StringBuilder("$txtTitle:\n")

            if (context != null) {
                val db = Room.databaseBuilder(requireContext(), MyRoomDB::class.java, ROOM_DATABASE_NAME)
                    .build()

                val person = Person(
                    //id = (System.currentTimeMillis() % Int.MAX_VALUE).toInt(),
                    name = Utils.getRandomStr(),
                    age = Random.nextInt(1, 120)
                )

                db.personDao().insertAll(person)

                sb.append("Inserted: id = ${person.id} with ${person.age} years old")
            }
            activity?.runOnUiThread {
                showResult(sb.toString())
            }
        }
    }

    override fun onButton2(v: View) {
        thread {
            val sb = StringBuilder("$txtTitle:\n")
            if (context != null) {
                val db = Room.databaseBuilder(context!!, MyRoomDB::class.java, ROOM_DATABASE_NAME)
                    .build()

                sb.append(">>> all\n")
                for (p in db.personDao().getAll())
                    sb.append(" - [${p.id}] ${p.name} {age = ${p.age}}\n")
                sb.append("\n>>> One with 30..40 years old\n")
                db.personDao().findOneByAge(30, 40)?.apply {
                    sb.append(" - [$id] $name {age = $age}\n")
                }

            }
            activity?.runOnUiThread {
                showResult(sb.toString())
            }
        }
    }

}