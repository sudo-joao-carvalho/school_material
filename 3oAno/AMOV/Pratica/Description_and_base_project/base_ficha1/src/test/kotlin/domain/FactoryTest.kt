package domain

import data.CustomRandom
import data.LocalizedPrice
import data.Product
import data.SizedProduct
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertNull

class FactoryTest {

    private val overrideRandomSerial = 1

    @BeforeEach
    fun setUp() {
        Factory.clear()
        CustomRandom.overrideValue = overrideRandomSerial
    }

    @Test
    fun alineaA2Test() {
        // Arrange
        val factory1 = Factory
        val factory2 = Factory

        // Act // Assert
        assert(factory1 === factory2)
    }

    @Test
    fun alineaBTest() {
        // Act
        val products = Factory.products
        val isCastable = products as? MutableList<Product>

        // Assert
        assertNull(isCastable)
    }

    @Test
    fun alineaC1Test() {
        // Arrange
        val portugal = LocalizedPrice.CountryCurrency.values().first()
        val price = LocalizedPrice(10.00, portugal)
        val testProduct = object : Product() {
            override val serialNumber: String = "SERIAL"
            override val name: String = "SampleProduct"
            override val prices: List<LocalizedPrice> = listOf(price)
        }

        // Act
        Factory.addProduct(testProduct)

        // Assert
        assert(Factory.products.contains(testProduct))
    }

    @Test
    fun alineaC2Test() {
        // Arrange
        var callbackCalled = false
        val portugal = LocalizedPrice.CountryCurrency.values().first()
        val price = LocalizedPrice(10.00, portugal)
        val testProduct = object : Product() {
            override val serialNumber: String = "SERIAL"
            override val name: String = "SampleProduct"
            override val prices: List<LocalizedPrice> = listOf(price)
        }

        // Act // Assert
        Factory.setProductCallback {
            callbackCalled = true
        }
        Factory.addProduct(testProduct)
        assert(callbackCalled)
    }

    @Test
    fun alineaDTest() {
        // Arrange
        val portugal = LocalizedPrice.CountryCurrency.values().first()
        val price = LocalizedPrice(10.00, portugal)
        val size = "M"
        val name = "SampleProduct"

        // Act
        val product = Factory.createSizeProduct(name, listOf(price), size)

        // Assert
        assert(product.name == name)
        assert(product.prices.contains(price))
        assert(product.serialNumber == "SIZED-$overrideRandomSerial")
        assert(product is SizedProduct)
    }

    @Test
    fun alineaETest(){
        // Arrange
        val portugal = LocalizedPrice.CountryCurrency.values().first()
        val price = LocalizedPrice(10.00, portugal)
        val color = "Red"
        val name = "SampleProduct"

        // Act
        val product = Factory.createColorProduct(name, listOf(price), color)

        // Assert
        assert(product.name == name)
        assert(product.prices.contains(price))
        assert(product.serialNumber == "COLORED-$overrideRandomSerial")
        assert(product is Product.WithColor)
    }

    @Test
    fun alineaFTest() {
        // Arrange
        val portugal = LocalizedPrice.CountryCurrency.values().first()
        val price = LocalizedPrice(10.00, portugal)
        val color = "Red"
        val size = "M"
        val name = "SampleProduct"

        // Act
        val product = Factory.createMixedProduct(name, listOf(price), size, color)

        // Assert
        assert(product.name == name)
        assert(product.prices.contains(price))
        assert(product.serialNumber == "MIXED-$overrideRandomSerial")
        assert(product is Product.WithColor)
        assert(product is Product.WithSize)
    }

    @Test
    fun alineaG1Test() {
        // Arrange
        val portugal = LocalizedPrice.CountryCurrency.values().first()
        val price = LocalizedPrice(10.00, portugal)
        val testProduct = object : Product() {
            override val serialNumber: String = "SERIAL"
            override val name: String = "SampleProduct"
            override val prices: List<LocalizedPrice> = listOf(price)
        }
        Factory.addProduct(testProduct)

        // Act
        val product = Factory.findProduct(serialNumber = "SERIAL")

        // Assert
        assert(product == testProduct)
    }

    @Test
    fun alineaG2Test() {
        // Arrange
        val portugal = LocalizedPrice.CountryCurrency.values().first()
        val price = LocalizedPrice(10.00, portugal)
        val testProduct = object : Product() {
            override val serialNumber: String = "SERIAL"
            override val name: String = "SampleProduct"
            override val prices: List<LocalizedPrice> = listOf(price)
        }
        Factory.addProduct(testProduct)

        // Act
        val product = Factory.findProduct(name = "SampleProduct")

        // Assert
        assert(product == testProduct)
    }

}