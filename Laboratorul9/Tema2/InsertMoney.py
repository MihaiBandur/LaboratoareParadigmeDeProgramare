from TakeMoneySTM import TakeMoneySTM


class InsertMoney:
    def __init__(self):
        self.state_machine = TakeMoneySTM()

    def insert_10bani(self):
        return self.state_machine.insert_money(0.1)

    def insert_50bani(self):
        return self.state_machine.insert_money(0.5)

    def insert_1leu(self):
        return self.state_machine.insert_money(1.0)

    def insert_5lei(self):
        return self.state_machine.insert_money(5.0)

    def insert_10lei(self):
        return self.state_machine.insert_money(10.0)
