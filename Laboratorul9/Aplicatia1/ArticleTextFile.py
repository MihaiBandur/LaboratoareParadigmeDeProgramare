from TextFile import TextFile

class ArticleTextFile(TextFile):
    def __init__(self, title, author, paragraphs):
        super().__init__(title, author, paragraphs, "Article")

    def print_text(self):
        print(f"{self.template}: {self.title}\nAuthor: {self.author}\nContinut: ")
        for p in self.paragraphs:
            print(f"<{p}>")
