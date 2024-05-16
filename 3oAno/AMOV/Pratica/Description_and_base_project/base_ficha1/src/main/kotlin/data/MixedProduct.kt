package data

// Mixed Product class with size and color
data class MixedProduct(
    override val name: String,
    override val serialNumber: String,
    override val prices: List<LocalizedPrice>,
    override val size: String,
    override val color: String,
    /* Alínea F */
) : /* Alínea F */Product(), /* Alínea F */Product.WithColor, Product.WithSize {
    /* Alínea F */
    companion object{
        const val SERIAL_NUMBER_PREFIX = "MIXED"
    }
}