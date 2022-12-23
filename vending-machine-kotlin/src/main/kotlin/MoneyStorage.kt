import java.math.RoundingMode

class MoneyStorage (var balance: Double, val selectedProduct: Product, val capitalizedProductName: String){
    var currentSaleBalance = 0.00
    fun updateBalanceAfterSale(): Double {
        balance += selectedProduct.price*0.01
        return balance
    }

//    fun giveRefund(): String{
//        return "Your purchase for $capitalizedProductName has been cancelled. Here is your refund: £${moneyStorage.currentSaleBalance}."
//    }

//    fun takeCoins(coin: Coin): String {
//        currentSaleBalance += coin.value*0.01
//        val balanceRemaining = selectedProduct.price*0.01-coin.value*0.01
//        if (productStorage.checkStock()) {
//            return checkTotal(balanceRemaining)
//        } else {
//            return "$capitalizedProductName is not available."
//        }
//    }
//
//    private fun checkTotal(balanceRemaining: Double): String {
//        val change = (currentSaleBalance - selectedProduct.price*0.01).toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
//
//        if (currentSaleBalance == selectedProduct.price*0.01) {
//            updateMachineAfterSale()
//            return "$capitalizedProductName dispensed. Enjoy."
//        } else if (currentSaleBalance > selectedProduct.price*0.01) {
//            updateMachineAfterSale()
//            return "Here is your change: $change. $capitalizedProductName dispensed. Enjoy."
//        } else {
//            return "$capitalizedProductName costs £${selectedProduct.price * 0.01}. \n " +
//                    "You've paid £$currentSaleBalance. \n " +
//                    "£$balanceRemaining remaining."
//        }
//    }
}