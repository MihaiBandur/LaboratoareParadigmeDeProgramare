import copy

from File import  File
from  abc import ABC, abstractmethod

class TextFile(File, ABC):
    def __init__(self, title, author, paragraphs, template):
        super().__init__(title, author, paragraphs)
        self.template = template

    @abstractmethod
    def print_text(self):
        pass

    def clone(self):
        return  copy.copy(self)