from File import File
from JSONFile import JSONFile
from ArticleTextFile import ArticleTextFile
from BlogTextFile import BlogTextFile
from HTMLFile import HTMLFile


class FileFactory:
    @staticmethod
    def factory(file: str):
        title, author, paragraphs = File.read_file_from_stdin()
        if file == "html":
            return HTMLFile(title, author, paragraphs)
        elif file == "json":
            return JSONFile(title, author, paragraphs)
        elif file == "article":
            return ArticleTextFile(title, author, paragraphs)
        elif file == "blog":
            return BlogTextFile(title, author, paragraphs)
        else:
            raise ValueError("tip de fisier ciudat!")
