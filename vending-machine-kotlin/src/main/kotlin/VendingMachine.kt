class VendingMachine (val id: String, val inventory: MutableMap<Product, Int>, var balance: Double){
    fun getInventoryForProduct(product: Product): Int? {
        return inventory[product]
    }

    fun updateInventoryAfterSale(product: Product): MutableMap<Product, Int> {
        inventory.merge(product, 1, Int::minus)
        return inventory
    }

    fun updateBalanceAfterSale(product: Product): Double {
        balance += product.price*0.01
        return balance
    }

    fun takeCoins(product: Product) {

    }


}

enum class Product (val price: Int){
    COKE(45),
    PEPSI(35),
    SODA(25)
}

enum class Coin (val value: Int) {
    PENNY(1),
    NICKEL(5),
    DIME(10),
    QUARTER(25)
}

class Transactions (val id: Int, val item: Product, val total: Int) {

}