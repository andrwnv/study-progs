import sys, math

NUMBER_OF_GOLDEN_SECTION = 1.6180339887 

def res_of_default_function(arg: float) -> float:
    """
        calculate f(x) = 4x + e^x
    """

    return math.pow(arg - 2, 2) - 9

def calcGoldenSection(a: float, b: float, eps: float) -> None:
   
    while math.fabs(b - a) > eps:

        x1 = b - (b - a)/NUMBER_OF_GOLDEN_SECTION
        x2 = a + (b - a)/NUMBER_OF_GOLDEN_SECTION

        y1 = res_of_default_function(x1)
        y2 = res_of_default_function(x2)
        
        if y1 >= y2:
            a = x1
        else:
            b = x2

    x = (a + b) / 2
    print("X: " , x)

if __name__ == "__main__":

    eps = input('Enter eps: ')
    a = input('Enter begin of interval: ')
    b = input('Enter end of interval: ')

    calcGoldenSection(float(a), float(b), float(eps))