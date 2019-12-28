import math


__x_coordinates = []
__y_coordinates = []


def func(x) -> float:
    return math.sin(x)


def __tabulation(a: float, b: float, n: int):
    if __x_coordinates or __y_coordinates:
        __x_coordinates.clear()
        __y_coordinates.clear()

    __x_coordinates.append(a)
    h: float = (b - a) / n
    __y_coordinates.append(func(__x_coordinates[0]))

    for index in range(1, n):
        __x_coordinates.append(__x_coordinates[index - 1] + h)
        __y_coordinates.append(func(__x_coordinates[index]))


__a_coef = []
__b_coef = []
__c_coef = []


def parabloic_spline_init():
    for item in __y_coordinates:
        __a_coef.append(item)

    h: float = abs(__x_coordinates[1] - __x_coordinates[0])
    
    __b_coef.append(0)
    for i in range(len(__x_coordinates) - 1):
        __b_coef.append( (2 * (__y_coordinates[i + 1] - __y_coordinates[i]) / h) - __b_coef[i] )

    for i in range(1, len(__b_coef)): 
        __c_coef.append((__b_coef[i] - __b_coef[i - 1])/(2 * h))


def calc_approximation(x: float):
    searched_index = 0
    
    for i, item in enumerate(__x_coordinates):
        if item >= x:
            searched_index = i - 1
            break

    return __a_coef[searched_index] +\
           __b_coef[searched_index] * (x - __x_coordinates[searched_index]) +\
           __c_coef[searched_index] * math.pow((x - __x_coordinates[searched_index]), 2)

if __name__ == "__main__":
    __tabulation(0, 10, 100)

    parabloic_spline_init()

    print("aprox val {0}".format(calc_approximation(1.5)))
    print("real val {0}".format(math.sin(1.5)))