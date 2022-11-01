fun main() {
    // write your code here
    val read = readln().toInt()
    print(
        when {
            read < 0 -> "negative"
            0 < read -> "positive"
            else -> "zero"
        }
    )
}