fun main() {
    val line: String? = readLine()
    val message = "Elvis says: ${line ?: throw IllegalStateException()}"
    println(message)
}
