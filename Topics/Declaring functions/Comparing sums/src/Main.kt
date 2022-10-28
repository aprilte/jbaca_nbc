// write your function here
fun isGreater(a1: Int, a2: Int, b1: Int, b2: Int): Boolean = b1 + b2 < a1 + a2

fun main() {
    val number1 = readLine()!!.toInt()
    val number2 = readLine()!!.toInt()
    val number3 = readLine()!!.toInt()
    val number4 = readLine()!!.toInt()

    println(isGreater(number1, number2, number3, number4))
}
