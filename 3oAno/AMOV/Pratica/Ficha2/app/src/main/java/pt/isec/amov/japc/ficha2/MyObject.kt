package pt.isec.amov.japc.ficha2

object MyObject {

    private var _value = 1234

    public val value: Int
        get() {
            _value += 10
            return _value
        }

}