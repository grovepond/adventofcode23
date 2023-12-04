package day2

import com.grovepond.aoc23.Util


    fun main() {
        val input = "input/" + object {}.javaClass.enclosingClass.packageName + ".txt"
        val data = Util.readInput(input)
        //val  data = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\nGame 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\nGame 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\nGame 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\nGame 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green".split("\n")

        println(solvePart1(data)) //2720
        println(solvePart2(data)) //71535
    }

    fun solvePart1(data: List<String>): String {
        var count = 0
        data.forEach { line ->
            val game = line.substring(0, line.indexOf(':'))
            if (isValidGame(line.substringAfter(":")))
                count += game.split(" ")[1].toInt()
        }

        return count.toString()
    }

    fun solvePart2(data: List<String>): String {
        var count = 0
        data.forEach { line ->
            count += countMinCubes(line.substringAfter(":"))
        }

        return count.toString()
    }

    fun isValidGame(game: String): Boolean {
        var possible = true
        val draws = game.split(";")
        for (draw in draws) {
            val colours = draw.trim().split(",")
            for (colour in colours) {
                val parts = colour.trim().split(" ")
                if (parts[1] == "red") {
                    if (parts[0].toInt() > 12)
                        possible = false
                } else if (parts[1] == "green") {
                    if (parts[0].toInt() > 13)
                        possible = false
                } else if (parts[1] == "blue") {
                    if (parts[0].toInt() > 14)
                        possible = false
                }
            }
        }

        return possible
    }

    fun countMinCubes(game: String): Int {
        var minRed = 0
        var minGreen = 0
        var minBlue = 0
        val draws = game.split(";")
        for (draw in draws) {
            val colours = draw.trim().split(",")
            for (colour in colours) {
                val parts = colour.trim().split(" ")
                if (parts[1] == "red") {
                    if (parts[0].toInt() > minRed)
                        minRed = parts[0].toInt()
                } else if (parts[1] == "green") {
                    if (parts[0].toInt() > minGreen)
                        minGreen = parts[0].toInt()
                } else if (parts[1] == "blue") {
                    if (parts[0].toInt() > minBlue)
                        minBlue = parts[0].toInt()
                }
            }
        }

        return minRed * minGreen * minBlue
    }
