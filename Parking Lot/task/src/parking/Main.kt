package parking

import java.util.*

val input = Scanner(System.`in`)
var exit = false
fun main() {
    while (!exit) {
        val command = input.nextLine().split(' ')
        when {
            command[0] == "exit" -> exit = true
            command[0] == "leave" && command[1].toIntOrNull() in 1..CarPark.spots.size -> {
                CarPark.leave(command[1].toInt())
            }
            command.size != 3 -> println("Invalid Request")
            command[0] == "park" -> Car(command[1], command[2]).park()
        }
    }


}

class Car(val registration: String, val color: String) {
    fun park() {
        val spot = CarPark.spots.indexOfFirst { it == null }
        if (spot == -1) return println("Sorry, the parking lot is full.")
        CarPark.spots[spot] = this
        println("$color car parked in spot ${spot + 1}.")
    }
}

object CarPark {
    val spots: Array<Car?> = Array(20) {null}
    fun leave(index: Int) {
        if (spots[index - 1] == null) return println("There is no car in spot $index.")
        spots[index - 1] = null
        println("Spot $index is free.")
    }

}