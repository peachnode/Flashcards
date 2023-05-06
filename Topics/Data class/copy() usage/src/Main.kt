// do not change this data class
data class Box(val height: Int, val length: Int, val width: Int)

fun main() {
    // implement your code here
    val height = readln().toInt()
    val length1 = readln().toInt()
    val length2 = readln().toInt()
    val width = readln().toInt()
    val first = Box(height, length1, width)
    val second = first.copy(length = length2)
    println(first)
    println(second)

}