package data

// Product class with size
data class SizedProduct(
    override val name: String,
    /* Alínea D */
    override val serialNumber: String,
    override val prices: List<LocalizedPrice>,
    override val size: String
) : /* Alínea D */Product(), /* Alínea D */Product.WithSize {
    /* Alínea D */
    companion object{
        const val SERIAL_NUMBER_PREFIX: String = "SIZED"
    }
}
