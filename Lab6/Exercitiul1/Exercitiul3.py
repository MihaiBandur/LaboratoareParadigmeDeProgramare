import os


class AudioFile:
    def __init__(self, filename):
        if not filename.endswith(self.ext):
            raise Exception(f"Format nesuportat pentru {filename}")
        self.filename = filename

    def play(self):
        raise NotImplementedError("Metoda play() trebuie suprascrisă în subclase")


class MP3File(AudioFile):
    ext = ".mp3"

    def play(self):
        print(f"Se cântă {self.filename}, un fișier MP3.")


class WavFile(AudioFile):
    ext = ".wav"

    def play(self):
        print(f"Se cântă {self.filename}, un fișier WAV.")


class OggFile(AudioFile):
    ext = ".ogg"

    def play(self):
        print(f"Se cântă {self.filename}, un fișier OGG.")


class FlacFile(AudioFile):
    ext = ".flac"

    def play(self):
        print(f"Se cântă {self.filename}, un fișier FLAC.")

def ValidarePath():
    while True:
        path = input("Calea către directorul cu fișiere audio: ").strip()
        try:
            if not os.path.exists(path):
                raise FileNotFoundError(f"Eroare: Directorul '{path}' nu există. Introduceți o altă cale validă.")
                continue

            lista_fisiere = os.listdir(path)
            lista_fisiere_audio = [fisier for fisier in lista_fisiere
                                   if os.path.isfile(os.path.join(path, fisier)) and
                                   fisier.lower().endswith((".mp3", ".wav", ".ogg", ".flac"))]

            if  len(lista_fisiere_audio):
                return lista_fisiere_audio

            print("Nu exista niciun fisier audio, incercati alta cale ")




        except FileNotFoundError as e:
            print(e)

def TipulFisierului(lista):
    print(lista)
    while True:
        print(lista)
        fisier=input("Alegeti un fisier: ")
        if fisier in lista:
            if fisier.endswith(".mp3"):
                print(f"Fisierul '{fisier}' este mp3")
                mp3=MP3File(fisier)
                mp3.play()
            elif fisier.endswith(".wav"):
                print(f"Fisierul '{fisier}' este wav")
                wav = WavFile(fisier)
                wav.play()
            elif fisier.endswith(".ogg"):
                print(f"Fisierul '{fisier}' este ogg")
                ogg = OggFile(fisier)
                ogg.play()
            elif fisier.endswith(".flac"):
                print(f"Fisierul '{fisier}' este flac")
                flac = FlacFile(fisier)
                flac.play()

        else:
            print("Alegeti alt fisier nu exista cel ales")
        raspuns=input("Doriti sa parasiti programul da sau nu")
        if raspuns =="da":
            print("Program incheiat")
            break

if __name__ == "__main__":
    fisiere_audio = ValidarePath()
    TipulFisierului(fisiere_audio)