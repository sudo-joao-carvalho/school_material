import domain.Factory
import ui.FactoryUI

fun main() {
    println("Welcome to the Factory Exercise!")

    Factory.setProductCallback { serialNumber ->
        println("Product[$serialNumber] created with success!")
    }

    FactoryUI().start()
}

