import java.math.RoundingMode
import java.util.*

class VendingMachine (var id: String, var inventory: MutableMap<Product, Int>, var balance: Double){

    lateinit var selectedProduct: Product
    fun selectProduct(product: Product) {
        selectedProduct = product
    }

    private fun capitalizeProductName(selectedProduct: Product): String {
        return selectedProduct.name.lowercase(Locale.getDefault()).replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    val capitalizedProductName = capitalizeProductName(selectedProduct)
    var productStorage = ProductStorage(inventory, capitalizedProductName)
    var moneyStorage = MoneyStorage(balance, capitalizedProductName)



    private fun updateMachineAfterSale() {
        productStorage.updateInventoryAfterSale()
        moneyStorage.updateBalanceAfterSale()
        moneyStorage.currentSaleBalance = 0.00
    }





    fun reset() {
        id = ""
        inventory = mutableMapOf()
        balance = 0.0
    }

    //TODO: implement method to produce sales record

}
