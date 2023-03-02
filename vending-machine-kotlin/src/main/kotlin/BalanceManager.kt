import java.math.BigDecimal

class BalanceManager (var vendingMachineBalance: BigDecimal = BigDecimal(100)){
    var selectedProductPrice: BigDecimal = BigDecimal(0)
    var totalMoneyInserted: BigDecimal = BigDecimal(0)
    var leftToPay: BigDecimal = BigDecimal(0)

    fun getPrice(selectedProduct: Product) {
        selectedProductPrice =  convertCentToDollar(selectedProduct.price)
    }

    fun updateBalanceAfterSale(selectedProduct: Product) {
        getPrice(selectedProduct)
        vendingMachineBalance += selectedProductPrice
    }

    fun convertCentToDollar(price: Int): BigDecimal {
        return (price*0.01).toBigDecimal()
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

    fun getChange(): BigDecimal {
        return totalMoneyInserted - selectedProductPrice
    }
}