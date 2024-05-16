package data

data class LocalizedPrice(val price: Double, val countryCurrency: CountryCurrency) {

    enum class CountryCurrency(val countryCode: String, val currency: String) {
        /* Alínea I */
        PORTUGAL("PT", "EUR"),
        USA("US", "USD")
    }
}
