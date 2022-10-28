// write your function here

fun main() {
    val letter = readLine()!!.first()

    println(isVowel(letter))
}

fun isVowel(inp: Char): Boolean {
    val regm = Regex("[aiueoAIUEO]")
    return regm.matches(inp.toString())
}
