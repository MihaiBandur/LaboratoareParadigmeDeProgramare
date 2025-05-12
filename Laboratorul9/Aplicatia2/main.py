import os

def pipeline_files(paths):
    exist_files = (path for path in paths if os.path.isfile(path))
    txt_files = (path for path in exist_files if path.endswith('.txt'))
    for file in txt_files:
        with open(file, 'r') as f:
            nume_fisier = os.path.basename(file)
            numar_linii = len(f.readlines())
            yield f"{nume_fisier}: {numar_linii} linii"

if __name__ == '__main__':
    lista_paths = [
        'fisier.txt',
        'gabi.txt',
        'iftime.txt',
        'poza.jpg',
        'document.txt'
    ]

    with open('fisier.txt', 'w') as f:
        f.write("Linia1\nLinia2\nLinia3")
    with open('gabi.txt', 'w') as f:
        f.write("Doar o linie")
    with open('poza.jpg', 'w') as f:
        f.write("Eu nu exist")
    with open('iftime.txt', 'w') as f:
        f.write("Linia1\nLinia2\nLinia3\nLinia4")

    rez = pipeline_files(lista_paths)

    for rezultat in rez:
        print(rezultat)

    os.remove('fisier.txt')
    os.remove('gabi.txt')
    os.remove('poza.jpg')
    os.remove('iftime.txt')