import java.math.RoundingMode

class BalanceManager (var vendingMachineBalance: Double = 100.00, var selectedProductPrice: Double = 0.00, var totalMoneyInserted: Double = 0.00, var leftToPay: Double = 0.00){
    fun getPrice(selectedProduct: Product) {
        selectedProductPrice =  convertCentToDollar(selectedProduct.price)
    }

    fun updateBalanceAfterSale(selectedProduct: Product) {
        getPrice(selectedProduct)
        vendingMachineBalance += selectedProductPrice
    }

    fun convertCentToDollar(price: Int): Double {
        return price*0.01.toBigDecimal().setScale(2, RoundingMode.DOWN).toDouble()
    }

    fun takeCoinAndGetLeftToPay(selectedProduct: Product, coin: Coin) {
        totalMoneyInserted += convertCentToDollar(coin.value)
        leftToPay = convertCentToDollar(selectedProduct.price) - totalMoneyInserted
    }

    fun isFullyPaid(): Boolean {
        return totalMoneyInserted >= selectedProductPrice
    }

    fun isOverPaid(): Boolean {
        return totalMoneyInserted > selectedProductPrice
    }

    fun getChange(): Double {
        return totalMoneyInserted - selectedProductPrice
    }
}