package hr.kristiankliskovic.devcontrol.model

data class RGBValue(
    val R: Int,
    val G: Int,
    val B: Int,
) {
    fun displayColorString(): String {
        return "#${this.intToColorHexString(this.R)}${this.intToColorHexString(this.G)}${
            this.intToColorHexString(this.B)
        }"
    }

    private fun intToColorHexString(num: Int): String {
        var str = num.toString(16)
        if (str.length < 2) {
            str = "0$str"
        }
        return str.uppercase()
    }
}
