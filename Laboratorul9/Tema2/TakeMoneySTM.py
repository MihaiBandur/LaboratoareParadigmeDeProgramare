from Observable import Observable


class TakeMoneySTM(Observable):
    def __init__(self):
        super().__init__()
        self.current_state = "WaitingForClient"
        self.total_money = 0.0

    def insert_money(self, amount):
        self.total_money += amount
        self.notify_all(f"Total bani introduși: {self.total_money:.1f} lei")
        return self.total_money
