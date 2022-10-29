package converter

import java.math.BigInteger
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.pow

// Do not delete this line
const val ROUND_TABLE: Int = 5

fun main() {
    val conta = ConvDecimal()
}

class ConvDecimal() {
    var inputDec: BigInteger = BigInteger.ZERO
    var inputFloat: BigDecimal = BigDecimal.ZERO
    var floater = false
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
            } else if ( mode != "None" ) {
                println("radix supports 36 or lower")
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
                this.convSeq = "do"
            } catch (ex: Exception) {
                println("invalid input... available format:{source base} {target base}")
            }
        }
    }

    fun isEnableRadix(): Boolean = (1 < fromRadix) && (1 < toRadix)

    fun sconv() {
        this.convSeq = "do"
        print("Enter number in base ${fromRadix} to convert to base ${toRadix} (To go back type /back) > ")
        val inputter = readln()
        if (inputter == "/back") {
            this.convSeq = "back"
        } else {
            val regm = Regex("[0-9a-zA-Z]+")
            val regmf = Regex("[0-9a-zA-Z]+\\.[0-9a-zA-Z]+")
            if (regmf.matches(inputter)) {
                this.inputFloat = toGoodDecimal(inputter, this.fromRadix)
                this.floater = true
            } else {
                if (regm.matches(inputter)) {
                    this.inputDec = inputter.toBigInteger(this.fromRadix)
                    this.floater = false
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
            println("Conversion result: ${toFraction(this.inputFloat, this.toRadix)}\n")
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
    val intPartStr = inputVal.toBigInteger().toString(radix)
    var fract = inputVal - inputVal.toInt().toBigDecimal()
    var retStr = intPartStr + "."
    var condt = true
    var i = 0
    while (condt) {
        i++
        val test = fract * radix.toBigDecimal()
        val chk = test.toInt().toBigDecimal()
        fract = test - chk
        retStr += chk.toString().toBigInteger().toString(radix)
        if (fract == BigDecimal.ZERO) condt = false
        if (ROUND_TABLE <= i) condt = false
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
    val inSep = inStr.split(".")
    var intPart: BigInteger = BigInteger.ZERO
    var fractPart: BigDecimal = BigDecimal.ZERO
    if (inSep.size == 2) {
        intPart = inSep[0].toBigInteger(radix)
        fractPart = convertFraction(inSep[1], radix, inSep[1].length)
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
    var decimate: Double = 0.0
    inStr.withIndex().forEach {
        val setbyradix = it.value.toString().toBigInteger(radix)
        val based = 1.0 / (radix.toDouble().pow(it.index + 1 ))
        decimate += setbyradix.toDouble() * based
    }
    return decimate.toBigDecimal().setScale(rounder, RoundingMode.HALF_UP)
}