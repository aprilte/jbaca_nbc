fun main() {
    val divider = listOf(2, 3, 5, 6)
    val input = readln().toInt()

    if (input == 0) return

    divider.forEach {
        if (input % it == 0) {
            println("Divided by $it")
        }
    }
}
