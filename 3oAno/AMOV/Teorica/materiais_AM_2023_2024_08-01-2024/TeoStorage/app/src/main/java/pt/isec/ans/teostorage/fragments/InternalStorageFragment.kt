package pt.isec.ans.teostorage.fragments

import android.content.Context
import android.view.View
import pt.isec.ans.teostorage.Utils
import java.io.IOException
import java.io.PrintStream

class InternalStorageFragment : BaseStorageFragment() {
    val filename = FILENAME + "IS.txt"

    override val txtTitle: String
        get() = "Internal Storage"
    override val txtButton1 : String
        get() = "Write"
    override val txtButton2 : String
        get() = "Read"

    override fun onButton1(v: View) {
        try {
            var str = "nothing"
            context?.openFileOutput(filename, Context.MODE_APPEND).use {
                val ps = PrintStream(it)
                str = Utils.getRandomStr()
                ps.println(str)
            }
            showResult("Written in $txtTitle:\n$str")
        } catch (e: IOException) {
            showResult(e.toString())
        }
    }

    override fun onButton2(v: View) {
        try {
            val sb = StringBuilder("$txtTitle:\n")
            context?.openFileInput(filename)?.bufferedReader().use {
                it?.forEachLine {
                    sb.append(it + "\n")
                }
            }
            if (context!=null && !requireContext().deleteFile(filename))
                sb.append("\nError deleting")
            showResult(sb.toString())
        } catch (e: IOException) {
            showResult(e.toString())
        }
    }

}