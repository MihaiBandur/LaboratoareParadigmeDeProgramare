from HTMLFile import HTMLFile
from JSONFile import JSONFile
from TextFile import TextFile
from FileFactory import FileFactory

if __name__ == '__main__':
    file = input("Alege tipul de fisier: html/json/article/blog\n")
    file = FileFactory.factory(file)

    if isinstance(file, HTMLFile):
        file.print_html()
    elif isinstance(file, JSONFile):
        file.print_json()
    elif isinstance(file, TextFile):
        file.print_text()
