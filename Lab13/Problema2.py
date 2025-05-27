from  pprint import  pprint
import more_itertools


def main():
    data = "Aceasta este ultima saptmana inainte de sesiune, se apropie examenele".split(" ")
    key_func = lambda x: x[0].lower()
    word_func = lambda x: x
    reduce_func = lambda x: sorted(x)
    result = more_itertools.map_reduce(data, key_func, word_func, reduce_func)
    print(f"{result}")

if __name__ == '__main__':
    main()

