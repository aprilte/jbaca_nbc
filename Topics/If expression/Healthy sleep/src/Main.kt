fun main() {
    val ( timeRecomm, timeOverSlept, timeAnn ) = List(3){readln().toInt() }

    if( timeAnn < timeRecomm ){
        print("Deficiency")
    }else if( timeOverSlept < timeAnn  ){
        print("Excess")
    }else{
        print("Normal")
    }
}