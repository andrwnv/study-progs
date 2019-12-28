import math


def func(x) -> float:
    return math.sin(x)


class NewtonPolynomial:
    __x_coordinates = []
    __y_coordinates = []

    def __init__(self, a: float, b: float, n: int):
        self.__tabulation(a, b, n)

    def getX(self):
        return self.__x_coordinates

    def getY(self):
        return self.__y_coordinates

    def __tabulation(self, a: float, b: float, n: int):
        if self.__x_coordinates or self.__y_coordinates:
            self.__x_coordinates.clear()
            self.__y_coordinates.clear()

        self.__x_coordinates.append(a)
        h: float = (b - a) / n
        self.__y_coordinates.append(func(self.__x_coordinates[0]))

        for index in range(1, n):
            self.__x_coordinates.append(self.__x_coordinates[index - 1] + h)
            self.__y_coordinates.append(func(self.__x_coordinates[index]))

    def __calc_coefficient(self):
        n = len(self.__x_coordinates)
        arr = []

        for i in range(n):
            arr.append(self.__y_coordinates[i])

        for j in range(1, n):
            for i in range(n - 1, j - 1, -1):
                arr[i] = float(arr[i] - arr[i - 1]) / float(self.__x_coordinates[i] - self.__x_coordinates[i - j])

        return arr

    def calculate(self, x: float):
        a = self.__calc_coefficient()
        n = len(self.__x_coordinates) - 1
        p = a[n]

        for k in range(1, n + 1):
            p = a[n - k] + (x - self.__x_coordinates[n - k]) * p

        return p
