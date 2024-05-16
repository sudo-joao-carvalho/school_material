package ui

import data.LocalizedPrice
import data.SizedProduct
import domain.Factory

class FactoryUI {
    fun start() {
        while (true) {
            val choice = printMainMenu()
            when (choice) {
                1 -> createProduct(Factory.ProductType.Size)
                2 -> createProduct(Factory.ProductType.Color)
                3 -> createProduct(Factory.ProductType.Mixed)
                4 -> listProducts()
                5 -> findProduct()
                6 -> return
                else -> println("Invalid Option. Try again.")
            }
        }
    }

    private fun findProduct() {
        println("Search Product for:")
        println("1. Name")
        println("2. Serial Number (ex: ${SizedProduct.SERIAL_NUMBER_PREFIX}-123")
        println("3. Exit")
        print("option: ")
        val option = readlnOrNull()?.toIntOrNull() ?: return
        if (option == 3) {
            return
        }

        val product = if (option == 1) {
            println("Product Name: ");
            Factory.findProduct(name = readlnOrNull())
        } else {
            println("Product Serial Number: ");
            Factory.findProduct(serialNumber = readlnOrNull())
        }

        println(product?.toUIString() ?: "Product not found")
    }

    private fun listProducts() {
        // List all available products.
        println("[${Factory.products.size}] Available Products:")
        for (product in Factory.products) {
            println(product.toUIString())
        }
    }

    fun createProduct(productType: Factory.ProductType) {
        print("Product name: ")
        val nome: String = readlnOrNull() ?: ""

        val size: String? = if (productType == Factory.ProductType.Size || /* Alínea H */productType == Factory.ProductType.Mixed) {
            print("Size: ")
            readlnOrNull()
        } else {
            null
        }

        val color: String? = if (/* Alínea H */productType == Factory.ProductType.Color || productType == Factory.ProductType.Mixed) {
            print("Color: ")
            readlnOrNull()
        } else {
            null
        }

        val localizedPrices = printPriceMenu()

        when (productType) {
            Factory.ProductType.Size -> Factory.createSizeProduct(nome, localizedPrices, size ?: "")
            Factory.ProductType.Color -> Factory.createColorProduct(nome, localizedPrices, color ?: "")
            Factory.ProductType.Mixed -> Factory.createMixedProduct(nome, localizedPrices, size ?: "", color ?: "")
        }.also {
            /* Alínea H */
            Factory.addProduct(it)
        }
    }

    private fun printMainMenu(): Int? {
        println("Pick Product Type:")
        println("1. Sized")
        println("2. Colored")
        println("3. Mixed(Size & Color)")
        println("4. Show existing products")
        println("5. Find Product")
        println("6. Exit")
        print("Option: ")
        return readlnOrNull()?.toIntOrNull()
    }

    private fun printPriceMenu(): List<LocalizedPrice> {
        val localizedPrices = mutableListOf<LocalizedPrice>()

        while (true) {
            println("Choose the currency type:")
            LocalizedPrice.CountryCurrency.entries.forEachIndexed { index, currency ->
                println("${index + 1}. ${currency.currency} (${currency.countryCode})")
            }
            println("${LocalizedPrice.CountryCurrency.entries.size + 1}. Continue")
            print("Option: ")
            val currencyChoice = readlnOrNull()?.toIntOrNull()

            if (currencyChoice != null && currencyChoice >= 1 && currencyChoice <= LocalizedPrice.CountryCurrency.entries.size) {
                val selectedCurrency = LocalizedPrice.CountryCurrency.entries[currencyChoice - 1]
                print("Price (${selectedCurrency.currency}): ")
                val price = readlnOrNull()?.toDoubleOrNull()

                if (price != null) {
                    localizedPrices.add(LocalizedPrice(price, selectedCurrency))
                } else {
                    println("Invalid Price. Try again.")
                }
            } else if (currencyChoice == LocalizedPrice.CountryCurrency.entries.size + 1) {
                if (localizedPrices.isEmpty()) {
                    println("At least 1 price must be added.")
                } else {
                    break
                }
            } else {
                println("Invalid Option. Try again.")
            }
        }
        return localizedPrices.toList()
    }


}


