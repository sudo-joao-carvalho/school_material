package pt.isec.ans.teostorage.fragments

import android.content.ContentValues
import android.database.Cursor
import android.view.View
import pt.isec.ans.teostorage.database.MyDBHelper
import pt.isec.ans.teostorage.Utils
import kotlin.random.Random

class DBFragment : BaseStorageFragment() {
    override val txtTitle: String
        get() = "SQLite"
    override val txtButton1 : String
        get() = "Insert"
    override val txtButton2 : String
        get() = "Query"

    override fun onButton1(v: View) {
        val sb = StringBuilder("$txtTitle:\n")

        if (context != null) {
            val dbh = MyDBHelper(requireContext())

            val db = dbh.writableDatabase

            val values = ContentValues()
            values.put("id", (System.currentTimeMillis() % Int.MAX_VALUE).toInt())
            values.put("name", Utils.getRandomStr())
            values.put("age", Random.nextInt(1, 120))

            db.insert("people", null, values)

            sb.append("Inserted: $values")
        }
        showResult(sb.toString())
    }

    override fun onButton2(v: View) {
        val sb = StringBuilder("$txtTitle:\n")
        if (context != null) {
            val dbHelper = MyDBHelper(requireContext())

            val db = dbHelper.readableDatabase

            val cursor: Cursor = db.query("people",
                null, null, null,
                null, null, null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                sb.append("Id: ${cursor.getInt(0)}; "+
                        "Name: ${cursor.getString(1)}; "+
                        "Age: ${cursor.getInt(2)}\n")
                cursor.moveToNext()
            }
            cursor.close()
        }
        showResult(sb.toString())
    }

}