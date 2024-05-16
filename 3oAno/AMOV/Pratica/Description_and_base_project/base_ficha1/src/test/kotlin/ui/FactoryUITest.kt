package ui

import data.ColoredProduct
import data.LocalizedPrice
import data.MixedProduct
import data.SizedProduct
import domain.Factory
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test


class FactoryUITest {

    private val originalOut = System.out
    private val originalIn = System.`in`
    private val outputStreamCaptor = ByteArrayOutputStream()


    @BeforeEach
    fun setUp() {
        Factory.clear()
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @AfterEach
    fun cleanUp() {
        System.setOut(originalOut)
        System.setIn(originalIn)
    }

    @Test
    fun alineaH1Test() {
        // Arrange
        val price = LocalizedPrice(10.0, LocalizedPrice.CountryCurrency.values().first())
        val numberOfCurrencies = LocalizedPrice.CountryCurrency.values().size
        val sizedProduct = SizedProduct(
            "SampleProduct",
            "SERIAL",
            listOf(price),
            "Large"
        )

        val ui = FactoryUI()

        val keyboardInput =
            ByteArrayInputStream(
                ("1\n" +
                        "${sizedProduct.name}\n" +
                        "${sizedProduct.size}\n" +
                        "1\n" +
                        "${price.price}\n" +
                        "${numberOfCurrencies + 1}\n" +
                        "4\n" +
                        "6\n")
                    .toByteArray()
            )

        // Act
        System.setIn(keyboardInput)
        ui.start()

        // Assert
        assert(Factory.products.size == 1)
        val output = Factory.products[0]
        assert(output is SizedProduct)

        val sizeOutput = output as SizedProduct
        assert(sizeOutput.name == sizedProduct.name)
        assert(sizeOutput.size == sizedProduct.size)
        assert(sizeOutput.prices.size == sizedProduct.prices.size)
        assert(sizeOutput.prices[0].price == sizedProduct.prices[0].price)
        assert(sizeOutput.prices[0].countryCurrency == sizedProduct.prices[0].countryCurrency)
    }

    @Test
    fun alineaH2Test() {
        // Arrange
        val price = LocalizedPrice(10.0, LocalizedPrice.CountryCurrency.values().first())
        val numberOfCurrencies = LocalizedPrice.CountryCurrency.values().size
        val coloredProduct = ColoredProduct(
            "SampleProduct",
            "SERIAL",
            listOf(price),
            "Red"
        )

        val ui = FactoryUI()

        val keyboardInput =
            ByteArrayInputStream(
                ("2\n" +
                        "${coloredProduct.name}\n" +
                        "${coloredProduct.color}\n" +
                        "1\n" +
                        "${price.price}\n" +
                        "${numberOfCurrencies + 1}\n" +
                        "4\n" +
                        "6\n")
                    .toByteArray()
            )

        // Act
        System.setIn(keyboardInput)
        ui.start()

        // Assert
        assert(Factory.products.size == 1)
        val output = Factory.products[0]
        assert(output is ColoredProduct)

        val coloredOutput = output as ColoredProduct
        assert(coloredOutput.name == coloredProduct.name)
        assert(coloredOutput.color == coloredProduct.color)
        assert(coloredOutput.prices.size == coloredProduct.prices.size)
        assert(coloredOutput.prices[0].price == coloredProduct.prices[0].price)
        assert(coloredOutput.prices[0].countryCurrency == coloredProduct.prices[0].countryCurrency)
    }

    @Test
    fun alineaH3Test() {
        // Arrange
        val price = LocalizedPrice(10.0, LocalizedPrice.CountryCurrency.values().first())
        val numberOfCurrencies = LocalizedPrice.CountryCurrency.values().size
        val mixedProduct = MixedProduct(
            "SampleProduct",
            "SERIAL",
            listOf(price),
            "Large",
            "Red",
        )

        val ui = FactoryUI()

        val keyboardInput =
            ByteArrayInputStream(
                ("3\n" +
                        "${mixedProduct.name}\n" +
                        "${mixedProduct.size}\n" +
                        "${mixedProduct.color}\n" +
                        "1\n" +
                        "${price.price}\n" +
                        "${numberOfCurrencies + 1}\n" +
                        "4\n" +
                        "6\n")
                    .toByteArray()
            )

        // Act
        System.setIn(keyboardInput)
        ui.start()

        // Assert
        assert(Factory.products.size == 1)
        val output = Factory.products[0]
        assert(output is MixedProduct)

        val mixedOutput = output as MixedProduct
        assert(mixedOutput.name == mixedProduct.name)
        assert(mixedOutput.color == mixedProduct.color)
        assert(mixedOutput.size == mixedProduct.size)
        assert(mixedOutput.prices.size == mixedProduct.prices.size)
        assert(mixedOutput.prices[0].price == mixedProduct.prices[0].price)
        assert(mixedOutput.prices[0].countryCurrency == mixedProduct.prices[0].countryCurrency)
    }

}

