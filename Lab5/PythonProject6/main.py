import tkinter as tk
from queue import Queue

import functii


def main():
    chenar = tk.Tk()
    chenar.geometry('665x425')
    chenar.title("Exercitiul 1")

    label1 = tk.Label(chenar, text='List of numbers')
    label1.place(x=30, y=10)

    e1 = tk.Entry(chenar)
    e1.place(x=130, y=10)

    numbers_list = []
    message_queue = Queue()

    result_label = tk.Label(chenar, text="Results will appear here")
    result_label.place(x=130, y=100)

    message_label = tk.Label(chenar, text="Messages will appear here")
    message_label.place(x=130, y=150)

    button_add_list = tk.Button(chenar, text='Add List',
                                command=lambda: functii.add_to_list_with_message(e1, numbers_list, message_queue,
                                                                         message_label))
    button_add_list.place(x=500, y=10)

    button_filter_odd = tk.Button(chenar, text='Filter odd',
                                  command=lambda: functii.start_filter_odd(numbers_list, message_queue, message_label))
    button_filter_odd.place(x=500, y=200)

    button_filter_primes = tk.Button(chenar, text='Filter primes',
                                     command=lambda:functii.start_filter_primes(numbers_list, message_queue, message_label))
    button_filter_primes.place(x=500, y=350)

    button_sum_numbers = tk.Button(chenar, text='Sum numbers',
                                   command=lambda:functii.start_sum_numbers(numbers_list, message_queue, message_label))
    button_sum_numbers.place(x=500, y=375)

    chenar.after(500,functii.check_queue)

    chenar.mainloop()


if __name__ == '__main__':
    main()
