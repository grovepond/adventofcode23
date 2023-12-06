package day6

import com.grovepond.aoc23.Util
fun main() {
    val input = "input/" + object{}.javaClass.enclosingClass.packageName + ".txt"
    //val input = "input/" + object{}.javaClass.enclosingClass.packageName + "_sample.txt"
    val data = Util.readInput(input)
    val pz = Day6()
    println(pz.solvePart1(data))
    println(pz.solvePart2(data))
}

class Day6 {

    fun solvePart1 (input: List<String>): String {
        val times = input[0].split(Regex("\\D+")).filter{it.isNotEmpty()}.map{it.toLong()}
        val distances = input[1].split(Regex("\\D+")).filter{it.isNotEmpty()}.map{it.toLong()}
        return calculateWins(times, distances).toString()
    }

    fun solvePart2 (input: List<String>): String {
        val times = listOf(input[0].replace(Regex("[^\\d]"), "").toLong())
        val distances = listOf(input[1].replace(Regex("[^\\d]"), "").toLong())
        return calculateWins(times, distances).toString()
    }

   fun calculateWins(times: List<Long>, distances: List<Long>): Long {
        return times
            .zip(distances)
            .map { (time, distance) ->
                (0 until time).count { i -> i * (time - i) > distance }
            }
            .map { it.toLong() }
            .reduce { acc, value -> acc * value }
    }

}