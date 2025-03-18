import math
from threading import Thread
from queue import Queue
def is_prime(n) -> bool:
    if n <= 1:
        return False
    for i in range(2, int(math.sqrt(n) + 1)):
        if n % i == 0:
            return False
    return True

def make_prime_list(lista) -> list:
    return [x for x in lista if is_prime(x)]

def make_odd_list(lista) -> list:
    return [x for x in lista if x % 2 == 1]

def sumlist(lista) -> int:
    return sum(lista)

def add_to_list(entry_text, numbers_list):
    try:
        numbers = [int(num.strip()) for num in entry_text.split(',')]
        numbers_list.clear()
        numbers_list.extend(numbers)
        print(f"Lista actualizată: {numbers_list}")
    except ValueError:
        print("Te rog introdu numere valide.")

def filter_odd(numbers_list, queue):
    odd_numbers = make_odd_list(numbers_list)
    queue.put(f"Odd numbers: {odd_numbers}")

def filter_primes(numbers_list, queue):
    prime_numbers = make_prime_list(numbers_list)
    queue.put(f"Prime numbers: {prime_numbers}")

def sum_numbers(numbers_list, queue):
    total_sum = sumlist(numbers_list)
    queue.put(f"Sum of numbers: {total_sum}")

def display_message(message_queue, message_label):
    if not message_queue.empty():
        message = message_queue.get()
        message_label.config(text=message)

def add_to_list_with_message(e1, numbers_list, message_queue, message_label):
    try:
        add_to_list(e1.get(), numbers_list)
        message_queue.put("List updated with new numbers.")
        display_message(message_queue, message_label)
    except ValueError:
        message_queue.put("Te rog introdu numere valide.")
        display_message(message_queue, message_label)

def start_filter_odd(numbers_list, message_queue, message_label):
    thread = Thread(target=filter_odd, args=(numbers_list, message_queue))
    thread.start()
    thread.join()
    display_message(message_queue, message_label)

def start_filter_primes(numbers_list, message_queue, message_label):
    thread = Thread(target=filter_primes, args=(numbers_list, message_queue))
    thread.start()
    thread.join()
    display_message(message_queue, message_label)

def start_sum_numbers(numbers_list, message_queue, message_label):
    thread = Thread(target=sum_numbers, args=(numbers_list, message_queue))
    thread.start()
    thread.join()
    display_message(message_queue, message_label)

def check_queue(message_queue, message_label, chenar):
    if not message_queue.empty():
        message = message_queue.get()
        message_label.config(text=message)
    chenar.after(500, check_queue)