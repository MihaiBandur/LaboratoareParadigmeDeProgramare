import os
import time

from Subject import Subject, RealSubject, Strategy


class Proxy(Subject):
    def __init__(self, real_subject: RealSubject):
        self.real_subject = real_subject
        self.cache_file = "http_cache.txt"
        self.cache = {}
        self._load_cache()

    def _load_cache(self):
        if os.path.exists(self.cache_file):
            with open(self.cache_file, 'r', encoding='utf-8') as f:
                for line in f:
                    parts = line.strip().split('|||')
                    if len(parts) == 3:
                        url, timestamp, content = parts
                        self.cache[url] = (float(timestamp), content)

    def _save_cache(self):
        with open(self.cache_file, 'w', encoding='utf-8') as f:
            for url, (timestamp, content) in self.cache.items():
                f.write(f"{url}|||{timestamp}|||{content}\n")

    def _is_cache_valid(self, timestamp):
        return (time.time() - timestamp) < 3600  # 1 oră

    def request(self, url):
        current_time = time.time()

        if url in self.cache:
            timestamp, content = self.cache[url]
            if self._is_cache_valid(timestamp):
                print(f"Se returnează răspuns din cache pentru {url}")
                return content
            else:
                print(f"Cache expirat pentru {url}")

        content = self.real_subject.request(url)
        if content:
            self.cache[url] = (current_time, content)
            self._save_cache()
        return content


class NormalStrategy(Strategy):
    def handle_request(self):
        return "Cerere gestionată normal"


class HighLoadStrategy(Strategy):
    def handle_request(self):
        return "Cerere gestionată cu strategie pentru încărcare mare (se replică serviciul)"


class LoadBalancer:
    def __init__(self):
        self.request_count = 0
        self.last_reset_time = time.time()
        self.strategy = NormalStrategy()

    def check_request_rate(self):
        current_time = time.time()
        if current_time - self.last_reset_time > 60:
            self.request_count = 0
            self.last_reset_time = current_time

        self.request_count += 1
        if self.request_count > 10:
            self.strategy = HighLoadStrategy()
        else:
            self.strategy = NormalStrategy()

        return self.strategy.handle_request()
