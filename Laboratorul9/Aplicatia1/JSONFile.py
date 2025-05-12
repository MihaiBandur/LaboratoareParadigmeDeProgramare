import json
from File import File

class JSONFile(File):
    def __init__(self, title, author, paragraphs):
        super().__init__(title, author, paragraphs)

    def print_json(self):
        data = {
            "title": self.title,
            "author": self.author,
            "continut": self.paragraphs
        }
        print(json.dumps(data, indent=4))
