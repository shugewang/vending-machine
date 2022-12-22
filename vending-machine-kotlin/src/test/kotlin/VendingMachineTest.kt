import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.Ignore

internal class VendingMachineTest {
    val vendingMachine = VendingMachine("1", mutableMapOf(Product.COKE to 10, Product.PEPSI to 10, Product.SODA to 10), 100.00)

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
}