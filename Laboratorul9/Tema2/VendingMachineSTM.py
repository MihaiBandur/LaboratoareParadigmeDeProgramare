from SelectProductSTM import SelectProductSTM
from TakeMoneySTM import TakeMoneySTM
from Brand import Coca_Cola, Pepsi, Sprite, ChoiceObserver
from WaitingForClient import WaitingForClient
from Observable import Observable

class VendingMachineSTM:
    def __init__(self):
        self.select_product_stm = SelectProductSTM()
        self.take_money_stm = TakeMoneySTM()
        self.waiting_for_client = WaitingForClient()
        self.choice_observer = ChoiceObserver()

        self.products = {
            '1': Coca_Cola(),
            '2': Pepsi(),
            '3': Sprite()
        }

        self.select_product_stm.attach(self.choice_observer)
        self.take_money_stm.attach(self.choice_observer)

        print("Automatul a fost inițializat.")
        print("Așteptare client...")
        self.waiting_for_client.client_arrived()

    def run(self):
        while True:
            print("\nOpțiuni disponibile:")
            print("1. Selectare produs")
            print("2. Introducere bani")
            print("3. Finalizare cumpărare")
            print("4. Ieșire")

            choice = input("> ").strip()

            if choice == "1":
                self._handle_product_selection()
            elif choice == "2":
                self._handle_money_insertion()
            elif choice == "3":
                self._handle_purchase()
            elif choice == "4":
                print("La revedere!")
                break
            else:
                print("Opțiune invalidă!")

    def _handle_product_selection(self):
        print("\nProduse disponibile:")
        for code, product in self.products.items():
            print(f"{code}. {product.name} - {product.price} lei")
        product_choice = input("Selectați produsul (1-3): ")
        if product_choice in self.products:
            product = self.products[product_choice]
            self.select_product_stm.choose_product(product)
        else:
            print("Selectie invalida!")

    def _handle_money_insertion(self):
        try:
            amount = float(input("Introduceți suma (ex: 2.0): "))
            if amount > 0:
                self.take_money_stm.insert_money(amount)
            else:
                print("Suma trebuie să fie pozitivă!")
        except ValueError:
            print("Introduceți o sumă validă!")

    def _handle_purchase(self):
        if not self.select_product_stm.selected_product:
            print("Selectați mai întâi un produs!")
            return

        if self.take_money_stm.total_money < self.select_product_stm.selected_product.price:
            print("Fonduri insuficiente!")
            return

        change = self.take_money_stm.total_money - self.select_product_stm.selected_product.price
        print(f"\nTranzacție finalizată cu succes!")
        print(f"Produsul {self.select_product_stm.selected_product.name} a fost eliberat.")
        if change > 0:
            print(f"Rest returnat: {change:.1f} lei")

        self.select_product_stm.selected_product = None
        self.take_money_stm.total_money = 0.0
        print("\nAutomatul a revenit în starea de așteptare.")
        WaitingForClient().client_arrived()
