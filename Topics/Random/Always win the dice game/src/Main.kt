import kotlin.random.Random

fun createDiceGameRandomizer(n: Int): Int {
    var sum1 = 111
    var sum2 = 0
    var seeder = 0

    while (sum2 < sum1) {
        sum1 = 0
        sum2 = sum1
        val rand = Random(seeder)
        repeat(n) {
            sum1 += rand.nextInt(1, 7)
        }
        repeat(n) {
            sum2 += rand.nextInt(1, 7)
        }
//        println("say, ${sum1} <? ${sum2} @ $seeder")
        seeder++
    }
    return seeder
}