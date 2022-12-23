
class ProductStorage(val inventory: MutableMap<Product, Int>, val capitalizedProductName: String) {
    fun checkStock(): Boolean {
        return inventory[selectedProduct]!! > 0
    }

    fun getInventoryForProduct(product: Product): Int? {
        return inventory[product]
    }

    fun updateInventoryAfterSale(): MutableMap<Product, Int> {
        inventory.merge(selectedProduct, 1, Int::minus)
        return inventory
    }

    fun restock(product: Product, quantity: Int) {
        inventory.merge(product, quantity, Int::plus)
    }
}