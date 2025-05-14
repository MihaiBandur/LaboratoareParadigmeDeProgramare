from threading import Thread, Condition
import time

elemente = []
conditie = Condition()

class Consumator(Thread):
    def __init__(self):
        Thread.__init__(self)

    def consumator(self):
        global conditie, elemente
        with conditie:
            while len(elemente) == 0:
                print('mesaj de la consumator: nu am nimic disponibil')
                conditie.wait()
            elemente.pop()
            print('mesaj de la consumator : am utilizat un element')
            print('mesaj de la consumator : mai am disponibil',
                  len(elemente),
                  'elemente')
            conditie.notify()

    def run(self):
        for i in range(5):
            self.consumator()
            time.sleep(1)

class Producator(Thread):
    def __init__(self):
        Thread.__init__(self)

    def producator(self):
        global conditie, elemente
        with conditie:
            while len(elemente) == 10:
                print('mesaj de la producator : am 10 elemente, opresc productia')
                conditie.wait()
            print('mesaj de la producator : am disponibile',
                  len(elemente),
                  'elemente')
            elemente.append(1)
            print('mesaj de la producator : am produs',
                  len(elemente),
                  'elemente')
            conditie.notify()

    def run(self):
        for i in range(5):
            self.producator()
            time.sleep(1)

if __name__ == '__main__':
    producator = Producator()
    consumator = Consumator()
    producator.start()
    consumator.start()
    producator.join()
    consumator.join()
