from abc import abstractmethod


class Celula:
    @abstractmethod
    def get_nume(self)->str:
        raise Exception("NotImplementedError")

class FibraMusculara(Celula):
    def __init__(self):
        self.nume="";
        self.masa_musculara=float(0.0)

    def __init__(self, nume, masa):
        self.nume=nume
        self.masa_musculara=masa

    def get_nume(self) ->str:
        return self.nume

    def get_masa_musculara(self)->float:
        return self.masa_musculara


class FibraNervoasa(Celula):
    def __init__(self):
        self.nume=""
        self.lungime=float(0.0)

    def __init__(self,nume, lungime):
        self.nume=nume
        self.lungime=lungime

    def get_nume(self) ->str:
        return self.nume

    def get_lungime(self)->float:
        return self.lungime


class MuschiGeneric:
    def __init__(self):
        self.fibre = list[FibraMusculara]
        self.nume=""
        self.masa_musculara=float(0.0)
        self.scope=""

    def __init__(self, lista, nume, masa, scop):
        self.fibre = lista
        self.nume = nume
        self.masa_musculara = masa
        self.scope = scop

    def get_nume(self) ->str :
        return self.nume

    def get_masa_musculare(self)->float:
        return self.masa_musculara

    def get_scope(self)->str:
        return self.scope


class TruchiNervos:
    def __init__(self):
        self.nervi = list[FibraNervoasa]
        self.nume = ""
        self.lungime = float(0.0)
        self.specializare = ""

    def __init__(self, lista, nume, lungime, specializare):
        self.nervi = lista
        self.nume = nume
        self.lungime = lungime
        self.specializare = specializare

    def get_nume(self) -> str:
        return self.nume

    def get_lungime(self) -> float:
        return self.lungime

    def get_specializare(self) -> str:
        return self.specializare

class Muschi:
    def __init__(self):
        self.muscle = MuschiGeneric()
        self.nervi = TruchiNervos()

    def __init__(self, muschi, nervi):
        self.muscle = muschi
        self.nervi = nervi

    def get_nume_muscle(self) -> str:
        return self.muscle.get_nume()

    def get_nume_nerv(self) -> str:
        return self.nervi.get_nume()

    def get_masa_musculare(self) -> float:
        return self.muscle.get_masa_musculare()

    def get_scop(self) -> str:
        return self.muscle.get_scope()

    def get_lungime(self) -> float:
        return self.nervi.get_lungime()

    def get_specializare(self) -> str:
        return self.nervi.get_specializare()

    def afisare(self):
        print(self.get_nume_muscle() +
            ": are scopul "+ self.get_scop() +
            " si este inervat de: "+ self.get_nume_nerv()
            + " care are specializarea: " + self.get_specializare())

def calcul_masa_totala(muschi: list[Muschi]) -> float:
    return sum(m.get_masa_musculare() for m in muschi)

def afiseaza_muschi_locomotori(muschi: list[Muschi]):
    for m in muschi:
        if "locomotor" in m.get_scop().lower():
            print(m.get_nume_muscle())

if __name__ == "__main__":
    fm = FibraMusculara(nume="fibra", masa=1)
    fn = FibraNervoasa(nume="nerv", lungime=1)
    mb = MuschiGeneric(lista= [fm] * 10, nume="biceps", masa=10*fm.get_masa_musculara(), scop="FLexia antebratului pe brat")
    mt = MuschiGeneric(lista= [fm] * 10, nume="triceps", masa=10*fm.get_masa_musculara(), scop="Extnsia antebratului pe brat")
    fb = TruchiNervos(lista= [fn] * 10, nume="nurometer C5-C6", lungime=1, specializare="locomotor")
    fn = TruchiNervos(lista= [fn] * 10, nume="nurometer C6-C7", lungime=1, specializare="locomotor")
    biceps = Muschi(muschi=mb, nervi=fb)
    triceps = Muschi(muschi=mt, nervi=fn)
    biceps.afisare()
    triceps.afisare()
    muschi_definiti = [biceps, triceps]
    masa_totala = calcul_masa_totala(muschi_definiti)
    print(f"Masa musculară totală: {masa_totala}")
    afiseaza_muschi_locomotori(muschi_definiti)


