from TakeMoneySTM import TakeMoneySTM


class WaitingForClient:
    def __init__(self):
        self.state_machine = TakeMoneySTM()

    def client_arrived(self):
        print("\nUn client a fost detectat!")
