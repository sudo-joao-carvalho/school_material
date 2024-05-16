package ui

import data.*
import java.util.*

fun Product.toUIString(): String {
    val description = when (this) {
        is SizedProduct -> "Size: ${this.size}"
        is ColoredProduct -> "Color: ${this.color}"
        is MixedProduct -> "Size: ${this.size} \n\tColor: ${this.color}"
        else -> ""
    }
    return """ [${this.serialNumber}] ${this.name}
    $description
    Prices: ${
        this.prices.let {
            val strBuilder = StringBuilder()
            it.forEach { price ->
                strBuilder.append("\n\t* ${price.formatPrice()}")
            }
            strBuilder.toString()
        }
    }
    """
}
fun LocalizedPrice.formatPrice(): String {
    return String.format(Locale.US, "%.2f%s", price, countryCurrency.currency)
}