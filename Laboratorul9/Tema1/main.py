from KotlinHandler import KotlinHandler
from JavaHandler import JavaHandler
from BashHandler import BashHandler
from PythonHandler import PythonHandler
import Command

def main():
    kotlin_handler = KotlinHandler()
    python_handler = PythonHandler()
    java_handler = JavaHandler()
    bash_handler = BashHandler()

    kotlin_handler.set_next(python_handler).set_next(bash_handler).set_next(java_handler)

    file_path = input("Introdu calea catre fisier(fara extensie): ")
    try:
        with open(file_path, 'r') as f:
            content = f.read()
    except FileNotFoundError:
        print("Fisierul nu exista!")
        return

    file_type = kotlin_handler.handle(content)
    if not file_type:
        print("Tipul fisierului nu a putut fi determinat")
        return

    print(f"Fisierul este de tip: {file_type}")

    result = Command.execute(file_type, content)
    if result:
        print("\nRezultatul executiei: ")
        print(result.stdout)
        if result.stderr:
            print("Erori: ")
            print(result.stderr)
    else:
        print("Executie esuata!")

if __name__ == '__main__':
    main()
