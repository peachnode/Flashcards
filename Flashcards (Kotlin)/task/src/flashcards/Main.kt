package flashcards

import java.io.File


var box = mutableListOf<Card>()
var log = ""

data class Card(val term: String, var definition: String, var mistakes: Int = 0)

fun add() {
    println("The card:")
    val term: String = readln()
    for (c in box) {
        if (c.term == term) {
            println("The card \"$term\" already exists.")
            return
        }
    }
    println("The definition of the card:")
    val definition: String = readln()
    for (c in box) {
        if (c.definition == definition) {
            println("The definition \"$definition\" already exists.")
            return
        }
    }
    box.add(Card(term, definition))
    println("The pair (\"$term\":\"$definition\") has been added.")
}

fun ask() {
    println("How many times to ask?")
    val times: Int = readln().toInt()
    val entries = box.shuffled()
    for (card in entries) {
        println("Print the definition of \"${card.term}\":")
        when (val input = readln()) {
            card.definition -> println("Correct!")
            else -> {
                print("Wrong. The right answer is \"${card.definition}\"")
                card.mistakes += 1
                for (card in entries){
                    if(card.definition == input) {
                        print("but your definition is correct for \"${card.term}\"")
                        break
                    }
                }
                println(".")
            }
        }
    }
}

fun remove() {
    println("Which card?")
    val term: String = readln()
    for (c in box){
        if(c.term == term){
            box.remove(c)
            println("The card has been removed")
            return
        }

    }
    println("Can't remove \"$term\": there is no such card.")

}

fun export() {
    println("File name:")
    val name: String = readln()
    File(name).delete()
    for (card in box) {
        File(name).appendText("${card.term};${card.definition};${card.mistakes}\n")
    }
    println("${box.size} cards have been saved.")
}

fun import() {
    println("File name:")
    val file: String = readln()
    try {
        val lines = File(file).readLines()
        val importBox = mutableMapOf<String, String>()
        loop@ for (line in lines) {
            val (term, definition, mistakes) = line.split(';')
            for (c in box){
                if(c.term.equals(term)){
                    c.definition = definition
                    c.mistakes = mistakes.toInt()
                    break@loop
                }
            }
            val card = Card(term, definition, mistakes.toInt())
            println(card)
            box.add(card)

        }

        println("${lines.size} cards have been loaded.")
    } catch (e: Exception) {
        println("File not found.")
    }


}

fun saveLog() {
    println("File name:")
    val file: String = readln()
    File(file).writeText(log)
    println("The log has been saved:")
}

fun println(s: String) {
    log += s + "\n"
    kotlin.io.println(s)
}

fun readln(): String {
    val s = kotlin.io.readln()
    log += s + "\n"
    return s
}

fun main() {
    while (true) {
        println("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):")
        when (readln()) {
            "add" -> add()
            "remove" -> remove()
            "import" -> import()
            "export" -> export()
            "ask" -> ask()
            "exit" -> break
            "log" -> saveLog()
            "hardest card" -> hardestCard()
            "reset stats" -> resetStats()
        }
    }
    println("Bye bye!")

}

fun resetStats() {
    box = box.map{it.copy(mistakes = 0)}.toMutableList()
    println("Card statistics have been reset.")
}

fun hardestCard() {
    val maxMistakes = box.maxByOrNull { it.mistakes }?.mistakes ?: 0
    val cardsWithMostMistakes = box.filter { it.mistakes == maxMistakes }
    if(cardsWithMostMistakes.isEmpty()||maxMistakes==0){
        println("There are no cards with errors.")

    }else if(cardsWithMostMistakes.size == 1){
        println("The hardest card is \"${cardsWithMostMistakes.first().term}\". You have ${cardsWithMostMistakes.first().mistakes} errors answering it.")
    }else{
        val cardTerms = cardsWithMostMistakes.joinToString(", ") { "\"${it.term}\"" }
        println("The hardest cards are $cardTerms. You have $maxMistakes errors answering them")

    }

}
