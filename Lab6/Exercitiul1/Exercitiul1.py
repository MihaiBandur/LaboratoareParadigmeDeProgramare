class Persoana:
    def __init__(self, nume, email):
        self.nume=nume
        self.email=email

    def display(self):
        return  f"Nume persoana: {self.nume} \nEmail Perosna: {self.email}"

class Prieten(Persoana):
    def __init__(self, nume, email, telefon):
        super().__init__(nume,email)
        self.telefon=telefon

    def Telefon_de_numar_nou(self, nr_nou):
            self.telefon=''
            if len(nr_nou)!= 10:
                self.telefon=nr_nou
            else:
                print("Nu este un numar de telefon valid")

    def display(self):
        return f"{super().display()}\nNumar de telefon: {self.telefon}"



if __name__ == '__main__':
    P=Prieten("codrin", "@gmai.com", "13")
    print(P.display())