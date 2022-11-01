fun main() {
    val divider: Int = 2
    val input = readln().toInt()

    println(
        when {
            input % divider == 0 -> "EVEN"
            else -> "ODD"
        }
    )
}
