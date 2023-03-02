import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.Ignore

class BalanceManagerTest {
    private val balanceManager = BalanceManager(BigDecimal(100))

    @Test
    fun getPriceTest() {
        balanceManager.getPrice(Product.COKE)
        assertEquals(BigDecimal("0.45"), balanceManager.selectedProductPrice)
    }

    @Test
    fun updateBalanceAfterSaleTest() {
        balanceManager.updateBalanceAfterSale(Product.COKE)
        assertEquals(BigDecimal("100.45"), balanceManager.vendingMachineBalance)
    }

    @Test
    fun convertCentToDollarTest() {
        assertEquals(BigDecimal("0.25"), balanceManager.convertCentToDollar(25))
    }

    @Test
    fun getLeftToPayTest() {
        balanceManager.takeCoinAndGetLeftToPay(Product.COKE, Coin.DIME)
        assertEquals(BigDecimal("0.35"), balanceManager.leftToPay)
    }

    @Test
    fun isFullyPaidTest() {
        balanceManager.totalMoneyInserted = BigDecimal("0.45")
        balanceManager.selectedProductPrice = BigDecimal("0.45")
        assertEquals(true, balanceManager.isFullyPaid())
    }

    @Test
    fun isOverPaidTest() {
        balanceManager.totalMoneyInserted = BigDecimal("0.55")
        balanceManager.selectedProductPrice = BigDecimal("0.45")
        assertEquals(true, balanceManager.isOverPaid())
    }

    @Test
    fun getChangeTest() {
        balanceManager.totalMoneyInserted = BigDecimal("0.55")
        balanceManager.selectedProductPrice = BigDecimal("0.45")
        assertEquals(BigDecimal("0.10"), balanceManager.getChange())
    }
}