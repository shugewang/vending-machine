import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class VendingMachineTest {
    private val vendingMachine = VendingMachine("1", mutableMapOf(Product.COKE to 10, Product.PEPSI to 10, Product.SODA to 10), 100.00)

    @Test
    fun getInventoryForProductTest() {
        assertEquals(10, vendingMachine.getInventoryForProduct(Product.COKE))
    }

    @Test
    fun updateInventoryAfterSellingOneProductTest() {
        assertEquals(mutableMapOf(Product.COKE to 9, Product.PEPSI to 10, Product.SODA to 10), vendingMachine.updateInventoryAfterSale(Product.COKE))
    }

    @Test
    fun updateBalanceAfterSellingOneProductTest() {
        assertEquals(100.45, vendingMachine.updateBalanceAfterSale(Product.COKE))
    }

    @Test
    fun takeCoinsIncompleteSaleTest() {
        assertEquals("Coke costs £0.45. \n" +
                " You've paid £0.1. \n" +
                " £0.35 remaining.", vendingMachine.takeCoins(Product.COKE, Coin.DIME))
    }

    @Test
    fun takeCoinsCompleteSaleTest() {
        vendingMachine.takeCoins(Product.COKE, Coin.DIME)
        vendingMachine.takeCoins(Product.COKE, Coin.DIME)
        assertEquals("Coke dispensed. Enjoy.", vendingMachine.takeCoins(Product.COKE, Coin.QUARTER))
    }

    @Test
    fun takeCoinsGiveChangeCompleteSaleTest() {
        vendingMachine.takeCoins(Product.COKE, Coin.DIME)
        vendingMachine.takeCoins(Product.COKE, Coin.QUARTER)
        assertEquals("Here is your change: 0.15. Coke dispensed. Enjoy.", vendingMachine.takeCoins(Product.COKE, Coin.QUARTER))
    }

    @Test
    fun checkInventoryAndBalanceCompleteSaleTest() {
        vendingMachine.takeCoins(Product.COKE, Coin.DIME)
        vendingMachine.takeCoins(Product.COKE, Coin.QUARTER)
        vendingMachine.takeCoins(Product.COKE, Coin.QUARTER)
        assertEquals(100.45, vendingMachine.balance)
        assertEquals(mutableMapOf(Product.COKE to 9, Product.PEPSI to 10, Product.SODA to 10), vendingMachine.inventory)
    }

    @Test
    fun giveRefundTest() {
        vendingMachine.takeCoins(Product.COKE, Coin.DIME)
        vendingMachine.takeCoins(Product.COKE, Coin.QUARTER)
        assertEquals("Your purchase has been cancelled. Here is your refund: £0.35.", vendingMachine.giveRefund())
    }

    @Test
    fun productNotAvailableTest() {
        val vendingMachine = VendingMachine("1", mutableMapOf(Product.COKE to 0, Product.PEPSI to 10, Product.SODA to 10), 100.00)
        assertEquals("Coke is not available.", vendingMachine.takeCoins(Product.COKE, Coin.DIME))
    }



}