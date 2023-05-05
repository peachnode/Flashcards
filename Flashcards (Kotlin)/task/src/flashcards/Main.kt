package flashcards

val box = mutableMapOf<String, String>()

fun main() {
    println("Input the number of cards:")
    val numberOfCards: Int = readln().toInt()
    repeat(numberOfCards) { index ->
        println("Card #${index + 1}:")
        var term: String = readln()
        while (box.containsKey(term)) {
            println("The term \"$term\" already exists. Try again:")
            term = readln()
        }
        println("The definition for card #${index + 1}:")
        var definition: String = readln()
        while (box.containsValue(definition)) {
            println("The definition \"$definition\" already exists. Try again:")
            definition = readln()
        }
        box.put(term, definition)

    }
    val rMap = box.entries.associate { (k,v)-> v to k }
    for ((k, v) in box) {
        println("Print the definition of \"${k}\":")
        val input = readln()
        when (input) {
            v -> println("Correct!")
            else -> {
                print("Wrong. The right answer is \"${v}\"")
                if(rMap.containsKey(input)){
                    print("but your definition is correct for \"${rMap[input]}\"")
                }
                println(".")

            }
        }
    }

}
