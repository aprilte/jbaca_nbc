fun main() {
    // write your code here
    val a = readln().toBigInteger()
    val b = readln().toBigInteger()
    val q = a.divide(b)
    val r = a.mod(b)
    println("${a} = ${b} * ${q} + ${r}")
}