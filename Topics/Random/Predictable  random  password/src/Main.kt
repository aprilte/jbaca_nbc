import kotlin.random.Random

fun generatePredictablePassword(seed: Int): String {
    var randomPassword = ""
    val rand = Random(seed)
    repeat(10) {
        randomPassword += rand.nextInt(33, 127).toChar()
    }
    // write your code here
    return randomPassword
}
