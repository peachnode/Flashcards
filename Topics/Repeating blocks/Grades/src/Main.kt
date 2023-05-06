fun main() {
    // put your code here
    var d: Int = 0
    var c: Int = 0
    var b: Int = 0
    var a: Int = 0
    val students: Int = readln().toInt()
    repeat(students) {
        val grade: Int = readln().toInt()
        when (grade) {
            2 -> d++
            3 -> c++
            4 -> b++
            5 -> a++
        }
    }
    println("$d $c $b $a")
}
