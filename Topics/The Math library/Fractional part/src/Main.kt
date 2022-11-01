import kotlin.math.*

fun main() {
    // put your code here
    val input = readln().toDouble()
    val firstD = (input - input.toInt().toDouble()) * 10.0
    println(firstD.toInt())
}