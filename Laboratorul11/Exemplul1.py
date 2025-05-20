import threading
import multiprocessing
from concurrent.futures import ThreadPoolExecutor
import time
def countdown():
    x = 100_000_000
    while x > 0:
        x -= 1
def ver_1():
    thread_1 = threading.Thread(target=countdown)
    thread_2 = threading.Thread(target=countdown)
    thread_1.start()
    thread_2.start()
    thread_1.join()
    thread_2.join()
def ver_2():
    countdown()
    countdown()
def ver_3():
    process_1 = multiprocessing.Process(target=countdown)
    process_2 = multiprocessing.Process(target=countdown)
    process_1.start()
    process_2.start()
    process_1.join()
    process_2.join()
def ver_4():
    with ThreadPoolExecutor(max_workers=2) as executor:
        future = executor.submit(countdown())
        future = executor.submit(countdown())

if __name__ == '__main__':
    start = time.time()
    ver_1()
    end = time.time()
    print("\n Timp executie pseudoparalelism cu GIL")
    print(end - start)
    start = time.time()
    ver_2()
    end = time.time()
    print("\n Timp executie secvential")
    print(end - start)
    start = time.time()
    ver_3()
    end = time.time()
    print("\n Timp executie paralela cu multiprocessing")
    print(end - start)
    start = time.time()
    ver_4()
    end = time.time()
    print("\n Timp executie paralela cu concurrent.futures")
    print(end - start)