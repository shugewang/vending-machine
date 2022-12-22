import java.math.RoundingMode
import java.util.*

class VendingMachine (val id: String, val inventory: MutableMap<Product, Int>, var balance: Double){
    private var currentSaleBalance = 0.00

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

    fun takeCoins(product: Product, coin: Coin): String {
        currentSaleBalance += coin.value*0.01
        val balanceRemaining = product.price*0.01-coin.value*0.01
        return checkTotal(product, balanceRemaining)
    }

    private fun checkTotal(product: Product, balanceRemaining: Double): String {
        val capitalizedProductName = product.name.lowercase(Locale.getDefault()).replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        val change = (currentSaleBalance - product.price*0.01).toBigDecimal().setScale(2, RoundingMode.UP).toDouble()

        if (currentSaleBalance == product.price*0.01) {
            updateInventoryAfterSale(product)
            updateBalanceAfterSale(product)
            return "$capitalizedProductName dispensed. Enjoy."
        } else if (currentSaleBalance > product.price*0.01) {
            updateInventoryAfterSale(product)
            updateBalanceAfterSale(product)
            return "Here is your change: $change. $capitalizedProductName dispensed. Enjoy."
        } else {
            return "$capitalizedProductName costs £${product.price * 0.01}. \n " +
                    "You've paid £$currentSaleBalance. \n " +
                    "£$balanceRemaining remaining."
        }
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