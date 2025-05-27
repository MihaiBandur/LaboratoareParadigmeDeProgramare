# This is a sample Python script.
from functools import reduce
from itertools import zip_longest

def grouper(iterable, n, fillvalue = None):
    args = [iter(iterable)]*n
    return zip_longest(*args, fillvalue=fillvalue)

def print_hi(name):
    the_list = [1, 21, 75, 39, 7, 2, 35, 3, 31, 7, 8]
    the_list = list(filter(lambda x: x >= 5, the_list))
    print(f"{the_list}")
    the_list = list(grouper(the_list, 2))
    print(f"{the_list}")
    the_list = list(map(lambda x: x[0] * x[1], the_list))
    print(f"{the_list}")
    the_list = reduce(lambda a, b: a + b, the_list)
    print(f"{the_list}")

if __name__ == '__main__':
    print_hi('PyCharm')