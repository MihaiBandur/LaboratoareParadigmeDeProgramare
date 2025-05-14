import multiprocessing
import multiprocessing.pool
from multiprocessing import cpu_count
from concurrent.futures import ThreadPoolExecutor, ProcessPoolExecutor
import math
import time


# Decorator de cronometrare
def time_it(func):
    def wrapper(*args, **kwargs):
        begin = time.time()
        output = func(*args, **kwargs)
        end = time.time()
        print(func.__name__, "executed in", end - begin, "sec")
        return output

    return wrapper


def factorial0(n):
    result = 1
    for num in range(2, n + 1):
        result *= num
    return result


def factorial1(n):
    return math.factorial(n)


@time_it
def test_thread_pool_executor(function, list_of_integers):
    with ThreadPoolExecutor(cpu_count()) as pool:
        return list(pool.map(function, list_of_integers))


@time_it
def test_process_pool_executor(function, list_of_integers):
    with ProcessPoolExecutor(cpu_count()) as pool:
        return list(pool.map(function, list_of_integers))


@time_it
def test_pool0(function, list_of_integers):
    with multiprocessing.pool.Pool(cpu_count()) as pool:
        return pool.map(function, list_of_integers)


@time_it
def test_pool1(function, list_of_integers):
    with multiprocessing.Pool(cpu_count()) as pool:
        return pool.map(function, list_of_integers)


@time_it
def test_thread_pool(function, list_of_integers):
    with multiprocessing.pool.ThreadPool(cpu_count()) as pool:
        return pool.map(function, list_of_integers)


if __name__ == '__main__':
    list_of_integers = list(range(5, 2000))
    tests = [test_thread_pool_executor, test_process_pool_executor,
             test_pool0, test_pool1, test_thread_pool]

    for function in [factorial0, factorial1]:
        print("\nTesting:", function.__name__)
        for test in tests:
            test(function, list_of_integers)
