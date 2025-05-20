import threading
import queue
import math
import time

class WorkerThread(threading.Thread):
    def __init__(self, task_queue):
        super().__init__()
        self.task_queue = task_queue
        self.daemon = True

    def run(self):
        while True:
            task = self.task_queue.get()
            if task is None:
                # Stop signal
                self.task_queue.task_done()
                break
            function, args_list = task
            try:
                for arg in args_list:
                    function(arg)
            finally:
                self.task_queue.task_done()

class ThreadPool:
    def __init__(self, num_threads):
        self.tasks = queue.Queue()
        self.workers = [WorkerThread(self.tasks) for _ in range(num_threads)]
        for worker in self.workers:
            worker.start()

    def map(self, function, args_list):
        num_threads = len(self.workers)
        chunk_size = math.ceil(len(args_list) / num_threads)
        chunks = [args_list[i:i + chunk_size] for i in range(0, len(args_list), chunk_size)]
        for chunk in chunks:
            self.tasks.put((function, chunk))

    def join(self):
        self.tasks.join()

    def terminate(self):
        # Trimite semnale de oprire
        for _ in self.workers:
            self.tasks.put(None)
        # Așteaptă încheierea thread-urilor
        for worker in self.workers:
            worker.join()

    def __enter__(self):
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        self.terminate()

def do_something(x):
    print(f"Processing {x}...")
    time.sleep(1)
    print(f"Finished processing {x}.")

if __name__ == "__main__":
    args = range(20)

    with ThreadPool(4) as pool:
        pool.map(do_something, args)
        pool.join()
