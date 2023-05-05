package flashcards

fun main() {
    val term: String = readln()
    val definition: String = readln()
    if(readln()==definition){
        println("Your answer is right!")
    }else{
        println("Your answer is wrong...")
    }
}
