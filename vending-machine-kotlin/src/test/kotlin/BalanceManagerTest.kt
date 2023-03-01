import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.Ignore

class BalanceManagerTest {
    private val balanceManager = BalanceManager(100.00, 0.00, 0.00, 0.00)

    @Test
    fun updateBalanceAfterSaleTest() {
        balanceManager.updateBalanceAfterSale(Product.COKE)
        assertEquals(100.45, balanceManager.vendingMachineBalance)
    }

    @Test
    fun getPriceTest() {
        balanceManager.getPrice(Product.COKE)
        assertEquals(0.45, balanceManager.selectedProductPrice)
    }

    @Test
    fun convertCentToDollarTest() {
        assertEquals(0.25, balanceManager.convertCentToDollar(25))
    }
}