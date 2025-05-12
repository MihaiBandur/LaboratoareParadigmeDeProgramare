from abc import  ABC, abstractmethod

class File(ABC):
    def __init__(self, title, author, paragrpahs):
        self.title = title
        self.author = author
        self.paragraphs = paragrpahs

    @staticmethod
    def read_file_from_stdin():
        title = input("Titlul paginii: ")
        author = input("Autorul paginii: ")
        nr_par = int(input("Numarul de paragrafe este: "))
        paragraphs = []
        for i in range(nr_par):
            paragraph = input(f"Paragraf {i+1}")
            paragraphs.append(paragraph)
        return  title, author, paragraphs