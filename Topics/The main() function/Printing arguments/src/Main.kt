    fun main(args: Array<String>) {
        if (args.size != 3) {
            println("Invalid number of program arguments")
            return
        } else {
            args.withIndex().forEach {
                print("Argument ${it.index + 1}: ")
                print("${it.value}. It has ${it.value.length} characters")
                println()
            }
        }
        return
    }