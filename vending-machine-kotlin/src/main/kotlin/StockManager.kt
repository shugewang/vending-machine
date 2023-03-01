
class StockManager (var inventory: MutableMap<Product, Int> = mutableMapOf(Product.COKE to 0, Product.PEPSI to 10, Product.SODA to 10)){
    fun isProductAvailable(productName: String): Boolean{
        val selectedProduct = Product.valueOf(productName.uppercase())
        return Product.values().map {it.name }.contains(productName.uppercase()) && inventory[selectedProduct]!! > 0
    }

    fun updateInventoryAfterSale(selectedProduct: Product) {
        inventory.merge(selectedProduct, 1, Int::minus)
    }

    fun restock(selectedProduct: Product, quantity: Int) {
        inventory.merge(selectedProduct, quantity, Int::plus)
    }

    fun checkInventory() {
        println("Stock")
        for (product in inventory) {
            println("${product.key}: ${product.value}")
        }
    }
}