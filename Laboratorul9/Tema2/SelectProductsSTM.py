from Observable import Observable

class SelectProductSTM(Observable):
    def __init__(self):
        super().__init__()
        self.current_state = "SelectProduct"
        self.selected_product = None

    def choose_product(self, product):
        self.selected_product = product
        self.notify_all(f"Produs selectat: {product.name}, Preț: {product.price} lei")
        return product.price
