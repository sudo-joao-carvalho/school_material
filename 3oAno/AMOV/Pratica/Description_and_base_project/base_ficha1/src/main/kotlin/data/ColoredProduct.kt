package data

// Product class with color
data class ColoredProduct(
    override val name: String,
    /* Alínea E */
    override val serialNumber: String,
    override val prices: List<LocalizedPrice>,
    override val color: String,
) : /* Alínea E */Product(), /* Alínea E */Product.WithColor {
    /* Alínea E */
    companion object{
        const val SERIAL_NUMBER_PREFIX = "COLORED"
    }
}
