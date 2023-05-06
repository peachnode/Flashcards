fun main() {
    // put your code here
    val times = readln().toInt()
    var max = 0
    repeat(times){
        val digit = readln().toInt()
        if(digit%4==0&&digit>max) max = digit
    }
    println(max)
}