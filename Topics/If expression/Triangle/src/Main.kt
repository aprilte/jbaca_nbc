fun main() {
    val (a, b, c) = List(3) { readln().toInt() }

    if ( a == 0 || b == 0 || c == 0 ){
       return
    }

    println(
        when {
            a < b + c && b < a + c && c < a + b  ->  "YES"
            else                                 ->  "NO"
        }
    )
}
