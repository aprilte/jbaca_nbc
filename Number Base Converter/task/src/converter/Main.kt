package converter// Do not delete this line

fun main() {
    val conta = Converter()
    conta.convPrompt()
}

class Converter() {
    var inputDec: Int = 0
    var status: Boolean = false

    init {
        while(!status) {
            print("Enter number in decimal system:")
            try {
                val inputter = readln()
                if (inputter == "") {
                    status = true
                } else {
                    inputDec = inputter.toInt()
                    status = true
                }
            } catch (ex: Exception) {
                println("Invalid Format ... ((+_+))")
            }
        }
    }

    fun convPrompt() {
        print("Enter target base:")
        val inputter = readln()
        when (inputter) {
            "2", -> printInBin(this.inputDec)
            "8", -> printInOct(this.inputDec)
            "16", -> printInHex(this.inputDec)
            else -> printAnyType(this.inputDec)
        }
    }

    fun printAnyType(value: Int) {
        println("Well,well... I'll tell you everything.")
        printInBin(value)
        printInOct(value)
        printInHex(value)
    }

    fun printInBin(value: Int) {
        println("Conversion result: " + Integer.toBinaryString(value))
    }

    fun printInHex(value: Int) {
        println("Conversion result: ${String.format("%x", value)}")
    }

    fun printInOct(value: Int) {
        println("Conversion result: ${String.format("%o", value)}")
    }

}