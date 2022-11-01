fun main() {

    val a = readLine()!!.toInt()
    val b = readLine()!!.toInt()
    val c = readLine()!!.toInt()

    println(if (a > c) {
        // put your code here
        if (b < a) {
            a
        } else {
            b
        }
    } else {
        // and here
        if (b < c) {
            c
        } else {
            b
        }
    })
}