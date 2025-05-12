import requests
from abc import ABC, abstractmethod

class Subject(ABC):
    @abstractmethod
    def request(self, url):
        pass

class RealSubject(Subject):
    def request(self, url):
        print(f"Se face cerere reală către {url}")
        response = requests.get(url)
        return response.text

class Strategy(ABC):
    @abstractmethod
    def handle_request(self):
        pass
