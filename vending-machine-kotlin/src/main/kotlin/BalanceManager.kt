import java.math.RoundingMode

class BalanceManager {
    var vendingMachineBalance = 100.00
    var selectedProductPrice = 0.00
    var totalMoneyInserted = 0.00
    var leftToPay = 0.00

    fun updateBalanceAfterSale(selectedProduct: Product) {
        vendingMachineBalance += selectedProduct.price*0.01
    }

    fun getPrice(selectedProduct: Product) {
        selectedProductPrice =  roundPricing(selectedProduct.price*0.01)
    }

    fun roundPricing(price: Double): Double {
        return price.toBigDecimal().setScale(2, RoundingMode.DOWN).toDouble()
    }

    fun takeCoinAndGetLeftToPay(selectedProduct: Product, coin: Coin) {
        totalMoneyInserted += roundPricing(coin.value*0.01)
        leftToPay = roundPricing(selectedProduct.price*0.01-totalMoneyInserted)
    }

    fun isFullyPaid(): Boolean {
        return totalMoneyInserted >= selectedProductPrice
    }

    fun isOverPaid(): Boolean {
        return totalMoneyInserted > selectedProductPrice
    }

    fun getChange(): Double {
        return roundPricing(totalMoneyInserted - selectedProductPrice)
    }
}