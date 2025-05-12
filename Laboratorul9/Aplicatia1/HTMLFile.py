from File import  File

class HTMLFile(File):
    def __init__(self, title, author, paragraphs, paragrpahs):
        super().__init__(title, author, paragraphs)


    def print_html(self):
        print(f"<h1>{self.title}</h1>\n<p>by{self.author}</p>")
        for p in self.paragraphs:
            print(f"<p>{p}</p>")