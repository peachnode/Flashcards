package flashcards

import java.io.File


val box = mutableMapOf<String, String>()
fun add(){
        println("The card:")
        val term: String = readln()
        if(box.containsKey(term)) {
            println("The card \"$term\" already exists.")
            return
        }
        println("The definition of the card:")
        val definition: String = readln()
        if (box.containsValue(definition)) {
            println("The definition \"$definition\" already exists.")
            return
        }
    box[term] = definition
    println("The pair (\"$term\":\"$definition\") has been added.")
}
fun ask(){
    println("How many times to ask?")
    val times: Int = readln().toInt()
    val rMap = box.entries.associate { (k,v)-> v to k }
    val entries =box.keys.toList().shuffled()
    for ((index, key) in entries.withIndex()){
        if(index > times){
            break
        }
        val value: String? = box[key]
        println("Print the definition of \"${key}\":")
        when (val input = readln()) {
            value -> println("Correct!")
            else -> {
                print("Wrong. The right answer is \"${value}\"")
                if (rMap.containsKey(input)) {
                    print("but your definition is correct for \"${rMap[input]}\"")
                }
                println(".")
            }
        }
    }
}
fun remove(){
    println("Which card?")
    val term: String = readln()
    if(box.containsKey(term)){
        box.remove(term)
        println("The card has been removed")
    }else{
        println("Can't remove \"$term\": there is no such card.")
    }
}
fun export(){
    println("File name:")
    val name: String = readln()
    File(name).delete()
    for ((k,v) in box){
        File(name).appendText("$k;$v\n")
    }
    println("${box.size} cards have been saved.")
}

fun import(){
    println("File name:")
    val file: String = readln()
    try{
        val lines = File(file).readLines()
        val importBox = mutableMapOf<String, String>()
        for(line in lines){
            println(line)
            val (term, definition) = line.split(';')
            importBox.put(term, definition)
        }
        for ((k,v) in importBox){
            box.merge(k, v){oldval, newval -> newval}
        }
        println("${importBox.size} cards have been loaded.")
    }catch(e: Exception){
        println("File not found.")
    }



}
fun main() {
    while(true){
        println("Input the action (add, remove, import, export, ask, exit):")
        when(readln()){
            "add" -> add()
            "remove" -> remove()
            "import" -> import()
            "export" -> export()
            "ask" -> ask()
            "exit" -> break
        }
    }
    println("Bye bye!")

}
