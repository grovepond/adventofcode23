package day4

import com.grovepond.aoc23.Util
import kotlin.math.pow

fun main() {
    val input = "input/" + object{}.javaClass.enclosingClass.packageName + ".txt"
    //val input = "input/" + object{}.javaClass.enclosingClass.packageName + "_sample.txt"
    val data = Util.readInput(input)
    println(solvePart1(data)) //33950
    println(solvePart2(data)) //14814534
}

fun solvePart1 (data: List<String>): String {
    val matches = data.map { processLine(it) }
    return matches.sumOf { 2.0.pow(it - 1).toInt() }.toString()
}

fun solvePart2 (data: List<String>): String {
    val matches = data.map { processLine(it) }
    val cards = IntArray(matches.size) { 1 }
    matches.forEachIndexed { index, score ->
        repeat(score) {
            cards[index+it+1] += cards[index]
        }
    }
    return cards.sum().toString()
}

fun processLine (line: String): Int {
    val numbers = line.substringAfter("|").trim().split("\\s+".toRegex())
    val winners = line.substring(line.indexOf(':') + 1, line.indexOf('|')).trim().split(" ").toSet()
    return winners.intersect(numbers).size
}

fun processLines (data: List<String>, sum: Int): Int {
    for ((ix, line) in data.withIndex()) {
        val count = processLine(line)
        //for (i in 1..count) {
            return processLines(data.subList(ix + 1, ix + count), sum + 1)
            //sum += i
        //}
    }
    return sum
}

private fun parseCard(input: String): Int {
    val winningNumbers = input.substringAfter(":").substringBefore("|").split(" ").filter { it.isNotEmpty() }.toSet()
    val ourNumbers = input.substringAfter("|").split(" ").filter { it.isNotEmpty() }.toSet()
    return winningNumbers.intersect(ourNumbers).size
}