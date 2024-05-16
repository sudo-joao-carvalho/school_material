package pt.isec.ans.teostorage

import kotlin.random.Random

object Utils {
    var counter = 1
    fun getRandomStr(maxlen : Int = 20) : String {
        var str = String.format("[%03d] ", counter++)
        repeat (maxlen) {
            str += Random.nextInt(65,90).toChar()
        }
        return str
    }
}