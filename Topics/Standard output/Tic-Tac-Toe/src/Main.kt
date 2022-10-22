fun main() {
    var TTT = arrayOfNulls<Boolean>(9)
    TTT[0]=true
    TTT[3]=true
    TTT[5]=true
    TTT[7]=true

    printTTT(TTT)
}


fun printTTT(field: Array<Boolean?>){
    for (i in 0..8){
        if( field[i] == true ){
            print("O")
        }else{
            print("X")
        }


        if( i == 2 || i == 5) {
            if( i != 8) println()

        }else{
            if( i != 8) print(" ")

        }
    }
}