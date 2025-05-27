# This is a sample Python script.
from dataclasses import dataclass
from datetime import date
from functional import seq
from dateutil.relativedelta import relativedelta


# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.

@dataclass
class Person:
    first_name: str
    last_name: str
    date_of_birth: date
    email: str


persons = [Person("John", "Doe", date(1960, 11, 3), "jdoe@example.com"),
           Person("Ellen", "Smith", date(1992, 5, 13), "ellensmith@example.com"),
           Person("Jane", "White", date(1986, 2, 1), "janewhite@example.com"),
           Person("Bill", "Jackson", date(1999, 11, 6), "bjackson@example.com"),
           Person("John", "Smith", date(1975, 7, 14), "johnsmith@example.com"),
           Person("Jack", "Williams", date(2005, 5, 28), "")
           ]


def main():
    # Use a breakpoint in the code line below to debug your script.
    persons_seq = seq(persons)
    youngest = persons_seq.order_by(lambda x: x.date_of_birth).first()
    oldest = persons_seq.order_by(lambda x: x.date_of_birth).last()

    print(f"{youngest}")
    print(f"{oldest}")
    underage = persons_seq.filter(lambda x: relativedelta(date.today(), x.date_of_birth).years < 18).to_list()
    print(f"{underage}")
    emails = persons_seq.map(lambda x: x.email).to_list()
    print(f"emails {emails}")
    emails2 = persons_seq.map(lambda x: (x.first_name + " " + x.last_name, x.email)).to_dict()
    print(f"emails + name {emails2}")
    emails3 = persons_seq.map(lambda x: (x.email, x)).to_dict()
    print(f"emails + name {emails3}")
    months = persons_seq.group_by(lambda x: x.date_of_birth.month)
    print(f"grouped by month: {months}")
    partitions = persons_seq.partition(lambda x: x.date_of_birth.year <= 1980)
    print(f"{partitions}")
    names = persons_seq.map(lambda x: x.first_name).distinct().to_list()
    print("first names:", names)
    average_age = persons_seq.map(lambda x: relativedelta(date.today(), x.date_of_birth).years).average()
    print("Average age:", average_age)
    smiths = len(list(filter(lambda x: x.last_name == "Smith", persons_seq)))
    print("number of people called Smith:", smiths)
    optional = next((x for x in persons_seq if x.first_name == "Jhon"), None)
    if optional is not None:
        print(optional)
    else:
        print("No one named John was found")
    search_result = next((x.last_name for x in persons_seq if x.first_name == "Thomas"), None)
    if search_result is not None:
        print(f"{search_result}")
    else:
        print("No one named Thomas was found.")
    no_email = any(x.email == "" for x in persons_seq)
    print("any with missing email:", no_email)


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    main()

# See PyCharm help at https://www.jetbrains.com/help/pycharm/