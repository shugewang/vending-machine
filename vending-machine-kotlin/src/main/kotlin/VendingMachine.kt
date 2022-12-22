import java.math.RoundingMode
import java.util.*

class VendingMachine (var id: String, var inventory: MutableMap<Product, Int>, var balance: Double){
    private var currentSaleBalance = 0.00
    lateinit var selectedProduct: Product

    fun selectProduct(product: Product) {
        selectedProduct = product
    }

    fun getInventoryForProduct(product: Product): Int? {
        return inventory[product]
    }

    fun updateInventoryAfterSale(): MutableMap<Product, Int> {
        inventory.merge(selectedProduct, 1, Int::minus)
        return inventory
    }

    fun updateBalanceAfterSale(): Double {
        balance += selectedProduct.price*0.01
        return balance
    }

    fun takeCoins(coin: Coin): String {
        currentSaleBalance += coin.value*0.01
        val balanceRemaining = selectedProduct.price*0.01-coin.value*0.01
        if (checkStock()) {
            return checkTotal(balanceRemaining)
        } else {
            return "${capitalizeProductName()} is not available."
        }
    }

    private fun capitalizeProductName (): String {
        return selectedProduct.name.lowercase(Locale.getDefault()).replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    private fun checkTotal(balanceRemaining: Double): String {
        val change = (currentSaleBalance - selectedProduct.price*0.01).toBigDecimal().setScale(2, RoundingMode.UP).toDouble()

        if (currentSaleBalance == selectedProduct.price*0.01) {
            updateMachineAfterSale()
            return "${capitalizeProductName()} dispensed. Enjoy."
        } else if (currentSaleBalance > selectedProduct.price*0.01) {
            updateMachineAfterSale()
            return "Here is your change: $change. ${capitalizeProductName()} dispensed. Enjoy."
        } else {
            return "${capitalizeProductName()} costs £${selectedProduct.price * 0.01}. \n " +
                    "You've paid £$currentSaleBalance. \n " +
                    "£$balanceRemaining remaining."
        }
    }

    private fun updateMachineAfterSale() {
        updateInventoryAfterSale()
        updateBalanceAfterSale()
        currentSaleBalance = 0.00
    }

    private fun checkStock(): Boolean {
        return inventory[selectedProduct]!! > 0
    }

    fun giveRefund(): String{
        return "Your purchase for ${capitalizeProductName()} has been cancelled. Here is your refund: £$currentSaleBalance."
    }

    fun restock(product: Product, quantity: Int) {
        inventory.merge(product, quantity, Int::plus)
    }

    fun reset() {
        id = ""
        inventory = mutableMapOf()
        balance = 0.0
    }

    //TODO: implement method to produce sales record

}


class Transactions (val id: Int, val item: Product, val total: Int) {

}