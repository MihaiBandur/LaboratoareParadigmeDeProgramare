from TextFile import TextFile

class BlogTextFile(TextFile):
    def __init__(self, title, author, paragraphs):
        super().__init__(title, author, paragraphs, "Blog")

    def print_text(self):
        print(f"{self.title}")
        for p in self.paragraphs:
            print(f"{p}")
        print(f"\nBlog scris de catre: {self.author}")
