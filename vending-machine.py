# You need to design a Vending Machine which:
# Accepts coins of 1,5,10,25 Cents i.e. penny, nickel, dime, and quarter.
# Allows a user to select a product from Coke(45), Pepsi(35), Soda(25)
# Allows a user to get a refund by cancelling the request.
# Returns the selected product and remaining change, if any.
# Allows a reset operation for the vending machine supplier.


def get_selection():
    selection = input("Enter selection:").capitalize()
    return selection


def insert_money(selection, menu):
    accepted_values = [1, 5, 10, 25]
    total_value = 0
    required_value = menu[selection]

    while total_value < required_value:
        coin = input("Enter coin:")
        if coin == "cancel":
            print("You've cancelled your purchase. Here's your refund (%s cents)." % total_value)
            selection = ""
            break
        if int(coin) in accepted_values:
            total_value+= int(coin)
            remaining = required_value - total_value
            print("You've inserted %s cents." % coin)
            print("You've inserted %s total. %s remaining." % (total_value, remaining))
        else:
            print("Coin not accepted. Accepted values are: 1c, 5c, 10c, 25c.")

    return selection, total_value, required_value


def return_change(selection, menu, total_value, required_value):
    if selection in menu:
        if total_value > required_value:
            change_value = total_value - required_value
            print("Thank you. Here's your %s and your change (%s cents)" % (selection, change_value))
        else:
            print("Thank you. Here's your %s." % selection)
        return change_value


def main():
    menu = {"Coke": 45, "Pepsi": 35, "Soda": 25}
    selection = get_selection()
    selection, total_value, required_value = insert_money(selection, menu)
    return_change(selection, menu, total_value, required_value)


main()