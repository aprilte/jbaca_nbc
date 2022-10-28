package converter

import java.math.BigInteger
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.pow

// Do not delete this line
const val ROUND_TABLE: Int = 5

fun main() {

    println("cvtest::" + convertFraction("13a", 21, ROUND_TABLE).toString())
    println("toGoodDecimal::" + toGoodDecimal("4242.13a", 21))

    val ccc: BigDecimal = BigDecimal(10.234)
    println(toFraction(ccc, 7))
    val conta = ConvDecimal()
}

class ConvDecimal() {
    var inputDec: BigInteger = BigInteger.ZERO
    var inputFloat: BigDecimal = BigDecimal.ZERO
    var floater = false
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
        return (fromRadix != 0) && (toRadix != 0)
    }

    fun sconv() {
        this.convSeq = "do"
        print("Enter number in base ${fromRadix} to convert to base ${toRadix} (To go back type /back) > ")
        val inputter = readln()
        if (inputter == "/back") {
            this.convSeq = "back"
        } else {
            val regm = Regex("[0-9a-zA-Z]+")
            val regmf = Regex("[0-9a-zA-Z.]+")

            if (regm.matches(inputter)) {
                this.inputSomething = inputter
                this.inputDec = inputSomething.toBigInteger(this.fromRadix)
                this.floater = false
            } else {
                if (regmf.matches(inputter)) {
                    this.inputSomething = inputter
                    this.inputFloat = inputSomething.toBigDecimal()
                    this.floater = true
                } else {
                    println("invalid input format, 0-9_a-z_A-Z_'.'")
                    this.convSeq = "retry"
                }
            }
        }
    }

    fun convResult() {
        if (!floater) {
            println("Conversion result: ${this.inputDec.toString(this.toRadix)}\n")
        } else {
            println("TODO(convert bigdecimal to any radix)....\n")
        }
    }
}


/**
 * Bigdecimalの少数付き基数変換
 * @param[inputVal] 元の値(BigDecimal)
 * @param[radix] 変換したい基数
 * @return return 変換結果を文字列で返す XXX.xxxxx
 */
fun toFraction(inputVal: BigDecimal, radix: Int): String {
    val intPartStr = inputVal.toInt().toBigInteger().toString(radix)
    var fract = inputVal - inputVal.toInt().toBigDecimal()

    var retStr = intPartStr + "."
    var condt = true
    var i = 0
    while (condt) {
        i++
        val test = fract * radix.toBigDecimal()
        val chk = test.toInt().toBigDecimal()
        fract = test - chk
        retStr += chk.toInt().toString()
        if (fract == BigDecimal.ZERO) condt = false
        if (5 < i) condt = false
    }
    val roundBound = radix / 2
    //round-up by half-of-base
    println("last:${retStr.last()},,round in ${roundBound}..${radix}")

    if (retStr.last().toInt() in roundBound..radix) {
        retStr = retStr.dropLast(1)
        val rounder = retStr.last().toInt() + 1
        retStr = retStr.dropLast(1)
        retStr += rounder
    } else {
        retStr = retStr.dropLast(1)
    }
    return retStr
}

/**
 * 文字列として与えた数値系列を指定した基数において10進数へ変換し文字列で返す
 * @param[inStr] 入力文字列
 * @param[radix] 入力文字列の基数
 * @return return 10進数表現のBigDecimal　XXX.xxxxx
 */
fun toGoodDecimal(inStr: String, radix: Int): BigDecimal {
    var retVal: BigDecimal = BigDecimal.ZERO
    val inSep = inStr.split(".")
    var intPart: BigInteger = BigInteger.ZERO
    var fractPart: BigDecimal = BigDecimal.ZERO
    if (inSep.size == 2) {
        intPart = inSep[0].toBigInteger(radix)
        fractPart = convertFraction(inSep[1], radix, 5)
    } else {
        intPart = inSep[0].toBigInteger(radix)
    }

    return intPart.toBigDecimal() + fractPart
}

/**
 * 少数以下文字列を指定した基数における10進数へ変換する
 * @param[inStr] いつもの
 * @param[radix] いつもの
 * @param[rounder] 何桁までほしいか　0 <
 * @return return 10進数表現での　小数点以下部分のみをBigDecimalで返す 0.xxxxx:{5桁の場合}
 */
fun convertFraction(inStr: String, radix: Int, rounder: Int): BigDecimal {
    var retVal: BigDecimal = BigDecimal.ZERO
    var decimate: Double = 0.0
    inStr.withIndex().forEach {
        val setbyradix = it.value.toString().toBigInteger(radix)
        val based = 1.0 / (radix.toDouble().pow(it.index + 1 ))
        decimate += setbyradix.toDouble() * based
    }
    return decimate.toBigDecimal().setScale(rounder, RoundingMode.HALF_UP)
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

