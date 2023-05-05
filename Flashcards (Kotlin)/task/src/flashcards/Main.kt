package flashcards
val box = mutableListOf<Flashcard>()

data class Flashcard(val term: String, val definition: String)
fun main() {
    println("Input the number of cards:")
    val numberOfCards: Int = readln().toInt()
    repeat(numberOfCards) { index ->
            println("Card #${index+1}:")
            val term: String = readln()
            println("The definition for card #${index+1}:")
            val definition: String = readln()
            box.add(Flashcard(term, definition))

    }
    for (f in box){
        println("Print the definition of \"${f.term}\":")
        when(readln()){
            f.definition -> println("Correct!")
            else -> println("Wrong. The right answer is \"${f.definition}\".")
        }
    }

}
