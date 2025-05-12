import subprocess
from abc import ABC, abstractmethod

class FileHandler(ABC):
    def __init__(self):
        self.next = None

    def set_next(self, handler):
        self.next = handler
        return handler

    def handle(self, content):
        if self.can_handle(content):
            return self.get_type()
        elif self.next:
            return self.next.handle(content)
        else:
            return None

    @abstractmethod
    def can_handle(self,content):
        pass

    @abstractmethod
    def get_type(self):
        pass
