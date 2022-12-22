import java.math.RoundingMode
import java.util.*

class VendingMachine (val id: String, val inventory: MutableMap<Product, Int>, var balance: Double){
    private var currentSaleBalance = 0.00
    private lateinit var selectedProduct: Product

    fun getInventoryForProduct(product: Product): Int? {
        return inventory[product]
    }

    fun updateInventoryAfterSale(product: Product): MutableMap<Product, Int> {
        inventory.merge(product, 1, Int::minus)
        return inventory
    }

    fun updateBalanceAfterSale(product: Product): Double {
        balance += product.price*0.01
        return balance
    }

    fun selectProduct(product: Product) {
        selectedProduct = product
    }

    fun takeCoins(product: Product, coin: Coin): String {
        currentSaleBalance += coin.value*0.01
        val balanceRemaining = product.price*0.01-coin.value*0.01
        if (checkStock(product)) {
            return checkTotal(product, balanceRemaining)
        } else {
            return "${capitalizeProductName(product)} is not available."
        }
    }

    private fun capitalizeProductName (product: Product): String {
        return product.name.lowercase(Locale.getDefault()).replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    private fun checkTotal(product: Product, balanceRemaining: Double): String {
        val change = (currentSaleBalance - product.price*0.01).toBigDecimal().setScale(2, RoundingMode.UP).toDouble()

        if (currentSaleBalance == product.price*0.01) {
            updateInventoryAfterSale(product)
            updateBalanceAfterSale(product)
            currentSaleBalance = 0.00
            return "${capitalizeProductName(product)} dispensed. Enjoy."
        } else if (currentSaleBalance > product.price*0.01) {
            updateInventoryAfterSale(product)
            updateBalanceAfterSale(product)
            currentSaleBalance = 0.00
            return "Here is your change: $change. ${capitalizeProductName(product)} dispensed. Enjoy."
        } else {
            return "${capitalizeProductName(product)} costs £${product.price * 0.01}. \n " +
                    "You've paid £$currentSaleBalance. \n " +
                    "£$balanceRemaining remaining."
        }
    }

    private fun checkStock(product: Product): Boolean {
        return inventory[product]!! > 0
    }

    fun giveRefund(): String{
        return "Your purchase has been cancelled. Here is your refund: £$currentSaleBalance."
    }

    //TODO: give refund if user cancel purchase
    //TODO: terminate purchase if product is sold out
    //TODO: implement method to restock machine
    //TODO: implement method to produce sales record


}

enum class Product (val price: Int){
    COKE(45),
    PEPSI(35),
    SODA(25)
}

enum class Coin (val value: Int) {
    PENNY(1),
    NICKEL(5),
    DIME(10),
    QUARTER(25)
}

class Transactions (val id: Int, val item: Product, val total: Int) {

}