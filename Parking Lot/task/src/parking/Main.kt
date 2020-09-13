package parking

import java.util.*

val input = Scanner(System.`in`)
var exit = false
fun main() {
    while (!exit) {
        val command = input.nextLine().split(' ')
        when (command[0]) {
            "exit" -> exit = true
            "status" -> CarPark.status()
            "create" -> command[1].toIntOrNull()?.let { CarPark.create(it) }
            "leave"  -> CarPark.leave(command[1].toInt())
            "park" -> Car(command[1], command[2]).park()
        }
    }


}

class Car(val registration: String, val color: String) {
    fun park() {
        if (CarPark.spots.isEmpty()) return println("Sorry, a parking lot has not been created.")
        val spot = CarPark.spots.indexOfFirst { it == null }
        if (spot == -1) return println("Sorry, the parking lot is full.")
        CarPark.spots[spot] = this
        println("$color car parked in spot ${spot + 1}.")
    }
}

object CarPark {
    var spots: Array<Car?> = Array(0) {null}
    fun create(size: Int) {
        spots = Array(size) {null}
        println("Created a parking lot with $size spots.")
    }
    fun leave(index: Int) {
        if (spots.isEmpty()) return println("Sorry, a parking lot has not been created.")
        if (index !in 1..spots.size) return println("Sorry, there is no spot $index.")
        if (spots[index - 1] == null) return println("There is no car in spot $index.")
        spots[index - 1] = null
        println("Spot $index is free.")
    }
    fun status() {
        if (spots.isEmpty()) return println("Sorry, a parking lot has not been created.")
        if (spots.all { it == null }) return println("Parking lot is empty.")
        for (i in spots.indices) {
            if (spots[i] != null) {
                println("${i + 1} ${spots[i]?.registration} ${spots[i]?.color}")
            }
        }
    }

}