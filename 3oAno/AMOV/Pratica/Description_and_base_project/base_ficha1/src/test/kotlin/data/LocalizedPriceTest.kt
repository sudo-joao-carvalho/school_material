package data

import org.junit.jupiter.api.Test

class LocalizedPriceTest {
    @Test
    fun alineaI1Test() {
        // Arrange
        val portugal = LocalizedPrice.CountryCurrency.values().first()
        val localizedPrice = LocalizedPrice(10.00, portugal)

        // Act
        val currency = localizedPrice.countryCurrency.currency
        val countryCode = localizedPrice.countryCurrency.countryCode

        // Assert
        assert(currency == "EUR")
        assert(countryCode == "PT")
    }

}