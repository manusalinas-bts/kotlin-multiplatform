package org.example.project

fun race() {
    val car = Car(engine = Engine(50))
    car.startCar()
}


class Car(private val engine: Engine) {
    fun startCar() {
        engine.start()
    }
}

class Engine(private val horsePower: Int) {
    fun start() {
        println("Starting engine...")
    }
}