import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class VendingMachineTest {
    private val stockManager = StockManager(mutableMapOf(Product.COKE to 0, Product.PEPSI to 10, Product.SODA to 10))
    private val balanceManager = BalanceManager(BigDecimal(100))
    private val vendingMachine = VendingMachine(stockManager, balanceManager)

    //TODO: test for selecting product
    //TODO: test for displaying message if product is available/how much to pay etc
    //TODO: test for transaction process - insert coin, check if fully paid
    //TODO: insert coin - balanceManager or vendingMachine?
    //TODO: endTransaction - display message and update/reset machine
//    @Test
//    fun endTransactionTest() {
//        vendingMachine.endTransaction()
//        var expected = mutableMapOf(Product.COKE to 0, Product.PEPSI to 9, Product.SODA to 10)
//        assertEquals("", vendingMachine.stockManager.inventory)
//        assertEquals(mutableMapOf<Product,Int>(), vendingMachine.inventory)
//        assertEquals(0.0, vendingMachine.balance)
//    }

}