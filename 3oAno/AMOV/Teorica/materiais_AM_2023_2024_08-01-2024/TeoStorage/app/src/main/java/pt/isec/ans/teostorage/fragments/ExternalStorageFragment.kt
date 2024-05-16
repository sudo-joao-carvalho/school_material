package pt.isec.ans.teostorage.fragments

import android.os.Environment
import android.view.View
import pt.isec.ans.teostorage.Utils
import java.io.*

class ExternalStorageFragment : BaseStorageFragment() {
    val filename = FILENAME + "ES.txt"

    override val txtTitle: String
        get() = "External Storage"
    override val txtButton1 : String
        get() = "Write"
    override val txtButton2 : String
        get() = "Read"

    override fun onButton1(v: View) {
        try {
            var str = "nothing"
            if (isExternalStorageWritable()) {
                val file = File(context?.getExternalFilesDir(null), filename)
                FileOutputStream(file, true).use {
                    val ps = PrintStream(it)
                    str = Utils.getRandomStr()
                    ps.println(str)
                }
            }
            showResult("Written in $txtTitle:\n$str")
        } catch (e: IOException) {
            showResult(e.toString())
        }
    }

    override fun onButton2(v: View) {
        try {
            if (!isExternalStorageReadable()) {
                showResult("External Storage is not readable")
                return
            }
            val sb = StringBuilder("$txtTitle:\n")
            val file = File(context?.getExternalFilesDir(null), filename)
            FileInputStream(file).bufferedReader().use {
                it.forEachLine {
                    sb.append(it + "\n")
                }
            }
            if (!file.delete())
                sb.append("\nError deleting")
            showResult(sb.toString())
        } catch (e: IOException) {
            showResult(e.toString())
        }
    }

    // Checks if a volume containing external storage is available
    // for read and write.
    fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    // Checks if a volume containing external storage is available to at least read.
    fun isExternalStorageReadable(): Boolean {
        return Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
    }
}