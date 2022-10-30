import kotlin.random.Random

fun generateTemperature(seed: Int): String{
    // write your code here
    var str = ""
    val rand = Random(seed)
    repeat(7) {
        str += rand.nextInt(20, 31).toString() + " "
    }
    return str
}