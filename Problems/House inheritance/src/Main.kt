import java.util.*

val input = Scanner(System.`in`)
fun main() {
    val rooms = input.nextLine().toInt().coerceAtLeast(1)
    val price = input.nextLine().toInt().coerceAtLeast(0).coerceAtMost(1_000_000)
    val house = makeHouse(rooms, price)
    println(totalPrice(house))
}
fun makeHouse(rooms: Int, price: Int):House {
    return when (rooms) {
        1 -> Cabin(rooms, price)
        2, 3 -> Bungalow(rooms, price)
        4 -> Cottage(rooms, price)
        5, 6, 7 -> Mansion(rooms, price)
        else -> Palace(rooms, price)
    }
}
open class House(open val rooms: Int, open val price: Int) {
    protected open val coefficient = 1.0
    fun totalPrice(): Int = (coefficient * price).toInt()
}
fun totalPrice(house: House): Int = house.totalPrice()

class Cabin(override val rooms: Int, override val price: Int): House(rooms, price) {
    override val coefficient = 1.0
}
class Bungalow(override val rooms: Int, override val price: Int): House(rooms, price) {
    override val coefficient = 1.2
}
class Cottage(override val rooms: Int, override val price: Int): House(rooms, price) {
    override val coefficient = 1.25
}
class Mansion(override val rooms: Int, override val price: Int): House(rooms, price) {
    override val coefficient = 1.4
}
class Palace(override val rooms: Int, override val price: Int): House(rooms, price) {
    override val coefficient = 1.6
}