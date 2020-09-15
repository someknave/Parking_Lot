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
            "reg_by_color" -> CarPark.regByColor(command[1])
            "spot_by_color" -> CarPark.spotsByColor(command[1])
            "spot_by_reg" -> CarPark.spotByReg(command[1])
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
    fun regByColor(color: String) {
        if (spots.isEmpty()) return println("Sorry, a parking lot has not been created.")
        val registrations = mutableListOf<String>()
        for (car in spots) {
            if (car != null && car.color.toLowerCase() == color.toLowerCase()) {
                registrations += car.registration
            }
        }
        if (registrations.isEmpty()) return println("No cars with color $color were found.")
        println(registrations.joinToString(", "))
    }
    fun spotsByColor(color: String) {
        if (spots.isEmpty()) return println("Sorry, a parking lot has not been created.")
        val carSpots = mutableListOf<Int>()
        for (spot in spots.indices) {
            if (spots[spot] != null && spots[spot]?.color?.toLowerCase() == color.toLowerCase()) {
                carSpots += spot + 1
            }
        }
        if (carSpots.isEmpty()) return println("No cars with color $color were found.")
        println(carSpots.joinToString(", "))
    }
    fun spotByReg(registration: String) {
        if (spots.isEmpty()) return println("Sorry, a parking lot has not been created.")
        for (spot in spots.indices) {
            if (spots[spot] != null && spots[spot]?.registration == registration) {
                return println(spot + 1)
            }
        }
        return println("No cars with registration number $registration were found.")
    }
}