package converter// Do not delete this line

fun main() {
    val conta = Converter()

}

class Converter() {
    var inputDec: Int = 0
    var inputSomething:String = ""
    var status: Boolean = false
    var mode: String = "From"

    init {
        while(mode != "None") {
            cont()
            status = false
            println()
        }
    }

    fun cont() {
        print("Do you want to convert /from decimal or /to decimal? (To quit type /exit) ")
        val select = readln()
        if (select == "/from") {
            print("Enter number in decimal system:")
            convFromDec()
        } else if (select == "/to") {
            print("Enter source number:")
            mode = "To"
            convToDec()
        } else {
            status = true
            mode = "None"
        }
    }

    fun convFromDec() {
        while(!status) {
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
        print("Enter target base:")
        val inputter = readln()
        when (inputter) {
            "2", -> printInBin(this.inputDec)
            "8", -> printInOct(this.inputDec)
            "16", -> printInHex(this.inputDec)
            else -> printAnyType(this.inputDec)
        }
    }

    fun convToDec() {
        while(!status) {
            try {
                val inputter = readln()
                if (inputter == "") {
                    status = true
                } else {
                    inputSomething = inputter
                    status = true
                }
            } catch (ex: Exception) {
                println("Invalid Format ... ((+_+))")
            }
        }
        print("Enter source base:")
        val sourceBase = readln()
        print("Conversion to decimal result: ")
        when (sourceBase) {
            "2", -> println(inputSomething.toIntOrNull(2))
            "8", -> println(inputSomething.toIntOrNull(8))
            "16", -> println(inputSomething.toIntOrNull(16))
            else -> println(inputSomething)
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