package day4

import com.grovepond.aoc23.Util

fun main() {
    val input = "input/" + object{}.javaClass.enclosingClass.packageName + ".txt"
    //val input = "input/" + object{}.javaClass.enclosingClass.packageName + "_sample.txt"
    val data = Util.readInput(input)
    println(solvePart1(data))
    println(solvePart2(data))
}

fun solvePart1 (data: List<String>): String {
    var result = 0
    for (line in data) {
        val numbers = line.substringAfter("|").trim().split("\\s+".toRegex())
        val winners = line.substring(line.indexOf(':') + 1, line.indexOf('|')).trim().split(" ").toSet()
        var count = 0
        numbers.forEach { number ->
            if (winners.contains(number))
                count = if (count == 0) count + 1 else count * 2
        }
        result += count
    }

    return result.toString()
}

fun solvePart2 (data: List<String>): String {
    var result = ""

    return result
}
