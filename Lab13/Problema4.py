
from math import sqrt

def is_prime(n):
    if n < 0:
        n *= -1
    if n == 2:
        return True
    if n < 2:
        return False
    if n % 2 == 0:
        return False

    for d in range(3, int(sqrt(n)) + 1, 2):
        if n % d == 0:
            return False

    return True


def main():
    the_list = [2, 50, 51, 35, 49, 52, 70, 85, 91, 95]
    print(f"{the_list}")
    the_list = list(filter(lambda x: not is_prime(x), the_list))
    print(f"{the_list}")
    the_list = list(filter(lambda x: x % 2 != 0, the_list))
    print(f"{the_list}")
    the_list = list(filter(lambda x: x <= 50, the_list))
    print(the_list)



if __name__ == '__main__':
    main()

