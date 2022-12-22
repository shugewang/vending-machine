import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class VendingMachineTest {
    private val vendingMachine = VendingMachine("1", mutableMapOf(Product.COKE to 10, Product.PEPSI to 10, Product.SODA to 10), 100.00)

    @Test
    fun getInventoryForProductTest() {
        vendingMachine.selectProduct(Product.COKE)
        assertEquals(10, vendingMachine.getInventoryForProduct(vendingMachine.selectedProduct))
    }

    @Test
    fun updateInventoryAfterSellingOneProductTest() {
        vendingMachine.selectProduct(Product.COKE)
        assertEquals(mutableMapOf(Product.COKE to 9, Product.PEPSI to 10, Product.SODA to 10), vendingMachine.updateInventoryAfterSale())
    }

    @Test
    fun updateBalanceAfterSellingOneProductTest() {
        vendingMachine.selectProduct(Product.COKE)
        assertEquals(100.45, vendingMachine.updateBalanceAfterSale())
    }

    @Test
    fun takeCoinsIncompleteSaleTest() {
        vendingMachine.selectProduct(Product.COKE)
        assertEquals("Coke costs £0.45. \n" +
                " You've paid £0.1. \n" +
                " £0.35 remaining.", vendingMachine.takeCoins(Coin.DIME))
    }

    @Test
    fun takeCoinsCompleteSaleTest() {
        vendingMachine.selectProduct(Product.COKE)
        vendingMachine.takeCoins(Coin.DIME)
        vendingMachine.takeCoins(Coin.DIME)
        assertEquals("Coke dispensed. Enjoy.", vendingMachine.takeCoins(Coin.QUARTER))
    }

    @Test
    fun takeCoinsGiveChangeCompleteSaleTest() {
        vendingMachine.selectProduct(Product.COKE)
        vendingMachine.takeCoins(Coin.DIME)
        vendingMachine.takeCoins(Coin.QUARTER)
        assertEquals("Here is your change: 0.15. Coke dispensed. Enjoy.", vendingMachine.takeCoins(Coin.QUARTER))
    }

    @Test
    fun checkInventoryAndBalanceCompleteSaleTest() {
        vendingMachine.selectProduct(Product.COKE)
        vendingMachine.takeCoins(Coin.DIME)
        vendingMachine.takeCoins(Coin.QUARTER)
        vendingMachine.takeCoins(Coin.QUARTER)
        assertEquals(100.45, vendingMachine.balance)
        assertEquals(mutableMapOf(Product.COKE to 9, Product.PEPSI to 10, Product.SODA to 10), vendingMachine.inventory)
    }

    @Test
    fun giveRefundTest() {
        vendingMachine.selectProduct(Product.COKE)
        vendingMachine.takeCoins(Coin.DIME)
        vendingMachine.takeCoins(Coin.QUARTER)
        assertEquals("Your purchase for Coke has been cancelled. Here is your refund: £0.35.", vendingMachine.giveRefund())
    }

    @Test
    fun productNotAvailableTest() {
        val vendingMachine = VendingMachine("1", mutableMapOf(Product.COKE to 0, Product.PEPSI to 10, Product.SODA to 10), 100.00)
        vendingMachine.selectProduct(Product.COKE)
        assertEquals("Coke is not available.", vendingMachine.takeCoins(Coin.DIME))
    }

    @Test
    fun restockTest() {
        vendingMachine.restock(Product.COKE, 10)
        assertEquals(mutableMapOf(Product.COKE to 20, Product.PEPSI to 10, Product.SODA to 10), vendingMachine.inventory)
    }



}