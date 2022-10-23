package converter

import java.math.BigInteger

// Do not delete this line

fun main() {
    val conta = Converter()
}

class Converter() {
    var inputDec: BigInteger = BigInteger.ZERO
    var inputSomething:String = ""
    var status: Boolean = false
    var mode: String = "From"
    var fromRadix: Int = 10
    var toRadix: Int = 2
    var convSeq: String = "do"

    init {
        while(mode != "None") {
            cont()
            if (mode != "None" && this.isEnableRadix()) {
                while(convSeq != "back") {
                    sconv()//すこんぶ
                    if (convSeq == "do") {
                        convResult()
                    } else {
                    }
                }
            } else {
                if ( mode != "None" ) {
                    println("radix supports 36 or lower")
                }
            }
            status = false
            println()
        }
    }

    fun cont() {
        print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ")
        val select = readln().split(" ")
        if (select.first() == "/exit") {
            status = true
            mode = "None"
        } else {
            try {
                fromRadix = select.first().toInt()
                toRadix = select.last().toInt()
//                println("${fromRadix}->${toRadix}")
                this.convSeq = "do"
            } catch (ex: Exception) {
                println("invalid input... available format:{source base} {target base}")
            }
        }
    }

    fun isEnableRadix(): Boolean {
        return (fromRadix % 2 == 0) && (toRadix % 2 == 0) || true
    }

    fun sconv() {
        this.convSeq = "do"
        print("Enter number in base ${fromRadix} to convert to base ${toRadix} (To go back type /back) > ")
        val inputter = readln()
        if (inputter == "/back") {
            this.convSeq = "back"
        } else {
            val regm = Regex("[0-9a-zA-Z]+")
            if (regm.matches(inputter)) {
                this.inputSomething = inputter
                this.inputDec = inputSomething.toBigInteger(this.fromRadix)
            } else {
                println("invalid input format, 0-9_a-z_A-Z")
                this.convSeq = "retry"
            }
        }
    }

    fun convResult() {
        println("Conversion result: ${this.inputDec.toString(this.toRadix)}\n")
    }

    fun convFromDec() {
        while(!status) {
            try {
                val inputter = readln()
                if (inputter == "") {
                    status = true
                } else {
                    inputDec = inputter.toBigInteger()
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


    fun printAnyType(value: BigInteger) {
        println("Well,well... I'll tell you everything.")
        printInBin(value)
        printInOct(value)
        printInHex(value)
    }

    fun printInBin(value: BigInteger) {
        println("Conversion result: " + value.toString(2))
    }
    fun printInHex(value: BigInteger) {
        println("Conversion result: " + value.toString(16))
    }
    fun printInOct(value: BigInteger) {
        println("Conversion result: " + value.toString(8))
    }

}