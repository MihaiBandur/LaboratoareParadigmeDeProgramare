import os
from typing import List
from collections import Counter

class GenericFile:
    def get_path(self):
        raise Exception("NonImplementedError")

    def get_freq(self):
        raise Exception("NonImplementedError")

class TextAscii(GenericFile):
    def __init__(self, path: str, lista: List[int]):
        self.path_absolute = path
        self.frecvente = lista

    def get_path(self)->str:
        return self.path_absolute

    def get_freq(self)->List[int]:
        return self.frecvente

class XMLFile(TextAscii):
    def __init__(self, path: str, lista: List[int], tag: str):
        super().__init__(path,lista)
        self.first_tag=tag

    def get_first_tag(self)->str:
        return self.first_tag


class TextUnicode(GenericFile):
    def __init__(self, path: str, frecv: List[int]):
        self.path_absolute = path
        self.frecvente = frecv


    def get_path(self) -> str:
        return self.path_absolute


    def get_freq(self) -> List[int]:
        return self.frecvente


class Binary(GenericFile):
    def __init__(self, path: str, lista: List[int]):
        self.path_absolute = path
        self.frecvente = lista

    def get_path(self) -> str:
        return self.path_absolute

    def get_freq(self) -> List[int]:
        return self.frecvente

class BMP(Binary):
    def __init__(self, path: str, freq: List[int], width: int, height: int, bpp: int):
        super().__init__(path, freq)
        self.width=width
        self.height=height
        self.bpp=bpp

    def show_info(self) -> (int, int, int):
        return self.width, self.height, self.bpp


def is_ascii_XML(content:bytes)->bool:
    content = content.decode(errors="ignore")
    start_tag = content.find("<")
    end_tag = content.find(">")

    if start_tag != -1 and end_tag != -1:
        file_tag = content[start_tag + 1:end_tag].split()
        if file_tag:
            return True
    return False


def file_type(path:str, content:bytes)->GenericFile:
    counter = Counter(content)
    total = len(content)
    frecvente = [counter[i] for i in range(256)]

    if total !=0:
        null_chars = float(counter[0]/total)
        if null_chars >=0.3:
            return  TextUnicode(path=path, frecv=frecvente)
    else:
        print("Fisierul este gol")
    ascii_char_freq = (sum(frecvente[i] for i in range(9, 11)) + frecvente[13] +
                       sum(frecvente[i] for i in range(32, 128)))
    if total != 0:
        ascii_char_freq /=total

        if ascii_char_freq>=0.95:
            if is_ascii_XML(content):
                start_tag = content.find(b'<')
                end_tag = content.find(b'>')
                first_tag = str(content[start_tag + 1:end_tag].decode(errors="ignore").split())
                return XMLFile(path=path, lista=frecvente, tag=first_tag)
            else:
                return TextAscii(path=path, lista=frecvente)
    if content[:2] == b"BM":
        width = int.from_bytes(content[18:22], byteorder="little")
        height = int.from_bytes(content[22:26], byteorder="little")
        bpp = int.from_bytes(content[28:30], byteorder="little")
        return BMP(path=path, freq=frecvente, width=width, height=height, bpp=bpp)

    return Binary(path=path, lista=frecvente)

def main(start_dir:str):
    for root, subdirs, files in os.walk(start_dir):
        for file in os.listdir(root):
            file_path = os.path.join(root, file)
            if os.path.isfile(file_path):
                f = open(file_path, 'rb')
                try:
                    content = f.read()
                    file_obj = file_type(path=file_path, content=content)
                    #print(f"\n {file_path}\n")

                    if isinstance(file_obj, XMLFile):
                        print(f"XML ASCII {file_path}")
                    if isinstance(file_obj, TextUnicode):
                        print(f"UNICODE {file_path}")
                    if isinstance(file_obj, BMP):
                        w, h, b = file_obj.show_info()
                        print(f"BMP {file_path}, Dimensiuni({w, h}, Byte per pixel: {b}")
                finally:
                    f.close()

if __name__ == '__main__':
    path = input("Introducei path-ul absolut al fisierului\n")
    if os.path.isdir(path):
        print("Fisiere identificate")
        main(path)
    else:
        print("Nu ati introdus nicun director")



