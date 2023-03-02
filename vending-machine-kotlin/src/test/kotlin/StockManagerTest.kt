import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StockManagerTest {
    private val stockManager = StockManager(mutableMapOf(Product.COKE to 0, Product.PEPSI to 10, Product.SODA to 10))

    @Test
    fun isProductAvailableTest() {
        assertEquals(false, stockManager.isProductAvailable("coke"))
        assertEquals(true, stockManager.isProductAvailable("pepsi"))
    }

    @Test
    fun updateInventoryAfterSaleTest() {
        stockManager.updateInventoryAfterSale(Product.PEPSI)
        var expected = mutableMapOf(Product.COKE to 0, Product.PEPSI to 9, Product.SODA to 10)
        assertEquals(expected, stockManager.inventory)
    }

    @Test
    fun restockTest() {
        stockManager.restock(Product.COKE, 10)
        var expected = mutableMapOf(Product.COKE to 10, Product.PEPSI to 10, Product.SODA to 10)
        assertEquals(expected, stockManager.inventory)
    }

    @Test
    fun checkInventoryTest() {
        assertEquals("Stock\n" +
                "COKE: 0\n" +
                "PEPSI: 10\n" +
                "SODA: 10", stockManager.checkInventory())
    }
}