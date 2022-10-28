// write your code here
fun square(num: Int): Long = num.toLong() * num

/* Do not change code below */
fun main() {
    val number = readLine()!!.toInt()
    println(square(number))
}