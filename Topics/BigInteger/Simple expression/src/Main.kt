import java.math.BigInteger

fun main() {
    // write your code here
    val input1 = readln().toBigInteger()
    val input2 = readln().toBigInteger()
    val input3 = readln().toBigInteger()
    val input4 = readln().toBigInteger()

    val ret = -1.toBigInteger() * input1 * input2 + input3 - input4
    print(ret)
}