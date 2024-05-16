package domain

import data.ColoredProduct
import data.LocalizedPrice
import data.MixedProduct
import data.Product
import data.SizedProduct

typealias ProductCallback = (serialNumber: String) -> Unit

/* Alínea A */object Factory {
    enum class ProductType {
        Size, Color, Mixed
    }

    private val availableProducts = mutableListOf<Product>()/* Alínea B */
    val products: List<Product>
        get() =  availableProducts.toList()/* Alínea B */

    private var callback: ProductCallback? = null

    fun setProductCallback(callback: ProductCallback) {
        this.callback = callback
    }

    /* Alínea A */init {
        println("Factory has been initialized!")
    }

    fun addProduct(product: Product) {
        // Invoke the callback only if it is not null. (Safe call operator .?)
        /* Alínea C */ callback?.invoke(product.serialNumber)
        // Add a product to the catalog.
        /* Alínea C */ availableProducts.add(product)
    }

    // Factory methods for different product types
    fun createSizeProduct(name: String, price: List<LocalizedPrice>, size: String): Product {
        return SizedProduct(name, Product.generateSerialNumber(SizedProduct.SERIAL_NUMBER_PREFIX), price, size)
    }

    fun createColorProduct(name: String, price: List<LocalizedPrice>, color: String): Product {
        return ColoredProduct(name, Product.generateSerialNumber(ColoredProduct.SERIAL_NUMBER_PREFIX), price, color)
    }

    fun createMixedProduct(name: String, price: List<LocalizedPrice>, size: String, color: String): Product {
        return MixedProduct(name, Product.generateSerialNumber(MixedProduct.SERIAL_NUMBER_PREFIX), price, size, color)
    }

    fun findProduct(serialNumber  /* Alínea G */: String? = null, name:  /* Alínea G */ String? = null): Product? {
        return availableProducts.find {
            if (serialNumber != null) {
                it.serialNumber == serialNumber
            } else if (name != null) {
                /* Alínea G */
                it.name == name
            } else {
                false
            }
        }
    }

    fun clear() {
        availableProducts.clear()
    }

}
