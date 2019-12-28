#!/usr/bin/python3

def derivative(func, eps: float):
    
    y = 1.0
    for i in range(0, 10):
        
        delta: float = 0.1
        x = 1.2 + 0.1 * i

        f1 = (func(x + delta, y + delta) - func(x + delta, y - delta) - func(x - delta, y + delta) + func(x - delta, y - delta)) / ( 4 * delta**2 )
        
        delta /= 10
        
        f2 =  (func(x + delta, y + delta) - func(x + delta, y - delta) - func(x - delta, y + delta) + func(x - delta, y - delta)) / ( 4 * delta**2 )
        
        while float(abs(f1 - f2)) > eps:
            tmp = abs(f1 - f2)
            delta /= 10

            f1 = f2
            f2 =  (func(x + delta, y + delta) - func(x + delta, y - delta) - func(x - delta, y + delta) + func(x - delta, y - delta)) / ( 4 * delta**2 )

            if tmp < abs(f1 - f2): 
                break

    return f1


if __name__ == "__main__":
    eps = input("Input eps: ")
    print( derivative(lambda x, y: x**3 * y**2 + 2*y**3 * x , float(eps)) )