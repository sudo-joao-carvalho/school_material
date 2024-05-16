package data

// Common interface for all product types
abstract class Product {
    abstract val serialNumber: String
    abstract val name: String
    abstract val prices: List<LocalizedPrice>

    init {
        println("New product being created...")
    }

    companion object {
        fun generateSerialNumber(prefix: String?): String {
            val randomInt = CustomRandom.nextInt()
            return if (prefix == null) {
                randomInt.toString()
            } else {
                "$prefix-$randomInt"
            }
        }
    }
    // Interface for products with size
    interface WithSize {
        val size: String
    }

    // Interface for products with color
    interface WithColor {
        val color: String
    }
}