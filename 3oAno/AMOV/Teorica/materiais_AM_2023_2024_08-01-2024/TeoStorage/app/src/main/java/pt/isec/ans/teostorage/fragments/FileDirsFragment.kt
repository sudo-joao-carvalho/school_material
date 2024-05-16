package pt.isec.ans.teostorage.fragments

import android.os.Environment
import android.view.View

class FileDirsFragment : BaseStorageFragment() {
    override val txtTitle: String
        get() = "External Directories"
    override val txtButton1 : String
        get() = "App"
    override val txtButton2 : String
        get() = "Environment"

    override fun onButton1(v: View) {
        val sb = StringBuilder("$txtTitle:\n")
        val dirs = arrayOf(
            null,
            Environment.DIRECTORY_MUSIC,
            Environment.DIRECTORY_PODCASTS,
            Environment.DIRECTORY_RINGTONES,
            Environment.DIRECTORY_ALARMS,
            Environment.DIRECTORY_NOTIFICATIONS,
            Environment.DIRECTORY_PICTURES,
            Environment.DIRECTORY_MOVIES,
            Environment.DIRECTORY_DOWNLOADS,
            Environment.DIRECTORY_DCIM,
            Environment.DIRECTORY_DOCUMENTS
        )
        dirs.forEach{
            val dir = context?.getExternalFilesDir(it)?.absolutePath ?: "none"
            sb.append("> $it - $dir\n\n")
        }
        showResult(sb.toString())
    }

    override fun onButton2(v: View) {
        val sb = StringBuilder("$txtTitle:\n")
        val dirs = arrayOf(
            Environment.DIRECTORY_MUSIC,
            Environment.DIRECTORY_PODCASTS,
            Environment.DIRECTORY_RINGTONES,
            Environment.DIRECTORY_ALARMS,
            Environment.DIRECTORY_NOTIFICATIONS,
            Environment.DIRECTORY_PICTURES,
            Environment.DIRECTORY_MOVIES,
            Environment.DIRECTORY_DOWNLOADS,
            Environment.DIRECTORY_DCIM,
            Environment.DIRECTORY_DOCUMENTS
        )
        dirs.forEach{
            val dir = Environment.getExternalStoragePublicDirectory(it)?.absolutePath ?: "none"
            sb.append("> $it - $dir\n\n")
        }
        showResult(sb.toString())
    }

}