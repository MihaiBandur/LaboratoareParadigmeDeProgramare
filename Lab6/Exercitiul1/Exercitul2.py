class BaseClass:
    num_base_calls = 0

    def call_me(self, caller):
        print("Apel metoda din BaseClass, caller=",caller)
        self.num_base_calls+=1

class LeftSubclass(BaseClass):
    num_left_calls = 0

    def call_me(self, caller):
        print("Apel metoda din LeftSubClass, caller=", caller)
        BaseClass.call_me(self, "LeftSubClass")
        self.num_left_calls+=1

class RightSubclass(BaseClass):
    num_right_calls = 0

    def call_me(self, caller):
        print("Apel metoda din RightSubClass, caller = ", caller)
        BaseClass.call_me(self, "RightSubClass")
        self.num_right_calls += 1

class Subclass(LeftSubclass, RightSubclass):
    num_sub_class = 0

    def call_me(self, caller):
        print("Apel metoda din Subclass, caller=", caller )
        BaseClass.call_me(self, "SubClass")
        BaseClass.call_me(self, "SubClass")
        self.num_sub_class +=1

if __name__ == '__main__':
    subclass_instance = Subclass()
    print(Subclass.__mro__)
    subclass_instance.call_me("__main__")
    print(subclass_instance.num_sub_class, subclass_instance.num_left_calls, subclass_instance.num_right_calls, subclass_instance.num_base_calls)