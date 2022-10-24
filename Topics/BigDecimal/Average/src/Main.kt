import java.math.BigDecimal
import java.math.RoundingMode

fun main() {
    // write your code here
    val a = readln().toBigDecimalOrNull() ?: BigDecimal.ZERO
    val b = readln().toBigDecimalOrNull() ?: BigDecimal.ZERO
    val c = readln().toBigDecimalOrNull() ?: BigDecimal.ZERO
    val sum: BigDecimal = a!!.plus(b!!).plus(c!!)
    val avg: BigDecimal = sum.divide(BigDecimal("3"), 1, RoundingMode.HALF_UP)
    println(avg.setScale(0, RoundingMode.HALF_UP))
}