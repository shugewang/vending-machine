import java.util.*
import kotlin.system.exitProcess

class VendingMachine (var stockManager: StockManager, var balanceManager: BalanceManager){
    var selectedProduct: Product? = null

    fun selectProduct() {
        println("Please choose your item: ")
        val productName: String? = readLine()
        if (!productName.isNullOrEmpty()) {
            if (stockManager.isProductAvailable(productName)) {
                println("You have selected ${capitalizeString(productName)}")
                selectedProduct = Product.valueOf(productName.uppercase())
                balanceManager.getPrice(selectedProduct!!)
                println("Please pay £${balanceManager.selectedProductPrice}")
            } else {
                println("Sorry, ${capitalizeString(productName)} is not available right now.")
            }
        }
    }

    fun processPurchase() {
        if (selectedProduct != null) {
            while (!balanceManager.isFullyPaid()) {
                insertCoin()
            }
            if (balanceManager.isOverPaid()) {
                val change = balanceManager.getChange()
                println("Here is your change: £$change.")
            }
            endTransaction()
        }
    }

    fun insertCoin() {
        println("Please insert coin:")
        val userInput = readLine()
        if (userInput!!.uppercase() == "REFUND") {
            println("Your purchase for ${capitalizeString(selectedProduct!!.name)} has been cancelled. Here is your refund: £${balanceManager.totalMoneyInserted}.")
            exitProcess(0)
        } else {
            val coin = Coin.valueOf(userInput.uppercase())
            balanceManager.takeCoinAndGetLeftToPay(selectedProduct!!, coin)
            println("You've paid £${balanceManager.totalMoneyInserted}. £${balanceManager.leftToPay} remaining.")
        }
    }

    fun endTransaction() {
        println("${capitalizeString(selectedProduct!!.name)} dispensed. Enjoy.")
        updateMachineAfterSale()
    }

    private fun capitalizeString(selectedProductName: String): String {
        return selectedProductName.lowercase(Locale.getDefault()).replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    private fun updateMachineAfterSale() {
        stockManager.updateInventoryAfterSale(selectedProduct!!)
        balanceManager.updateBalanceAfterSale(selectedProduct!!)
        balanceManager.totalMoneyInserted = 0.00
    }

    //TODO: implement method to produce sales record
    //TODO: fix rounding
    //TODO: admin, restart after purchase and not in stock, support multiple ways of inserting coins, exceptions
    //TODO: testing
}

fun main() {
    var stockManager = StockManager()
    var balanceManager = BalanceManager()
    var vendingMachine = VendingMachine(stockManager, balanceManager)
    vendingMachine.selectProduct()
    vendingMachine.processPurchase()
    vendingMachine.stockManager.checkInventory()
}