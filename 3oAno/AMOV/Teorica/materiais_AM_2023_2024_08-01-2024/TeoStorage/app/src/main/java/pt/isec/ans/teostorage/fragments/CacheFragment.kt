package pt.isec.ans.teostorage.fragments

import android.view.View
import pt.isec.ans.teostorage.Utils
import java.io.*

class CacheFragment : BaseStorageFragment() {
    val filename = FILENAME + "cache.txt"

    override val txtTitle: String
        get() = "Cache"
    override val txtButton1 : String
        get() = "Write"
    override val txtButton2 : String
        get() = "Read"

    override fun onButton1(v: View) {
        try {
            var str = "nothing"
            //val file1 = File.createTempFile("AMOV_",".tmp",context?.cacheDir)
            //val file2 = File.createTempFile("AMOV_",".tmp",context?.externalCacheDir)
            val file = File(context?.cacheDir, filename)

            FileOutputStream(file, true).use {
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
            val file = File(context?.cacheDir, filename)
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
}