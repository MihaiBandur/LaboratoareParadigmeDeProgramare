from Observable import Observer

class Product:
    def __init__(self, name, price):
        self.name = name
        self.price = price

class Coca_Cola(Product):
    def __init__(self):
        super().__init__("Coca-Cola", 5.0)

class Pepsi(Product):
    def __init__(self):
        super().__init__("Pepsi", 4.5)

class Sprite(Product):
    def __init__(self):
        super().__init__("Sprite", 4.0)

class ChoiceObserver(Observer):
    def update(self, message):
        print(f"Observer: {message}")
