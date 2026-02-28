
fun smallestAbsent(nums: IntArray): Int {
    val map = mutableMapOf<Int, Int>()
    val average = nums.average().toInt()
    nums.forEach { value ->
        map[value] = 1
    }

    for (i in average+1..101) {
        println("$i")
        if (map[i] == null && i > 0)  return i
    }

    return 0
}

fun main() {
//    println("${smallestAbsent(intArrayOf(3, 5))}")
//    println("${smallestAbsent(intArrayOf(-1, 1, 2))}")
//    println("${smallestAbsent(intArrayOf(4, -1))}")
    println("${smallestAbsent(intArrayOf(98, 100))}")
}