#!/usr/bin/python3

import math

def simpson_rule(func, interval_begin: float, interval_end: float, step):
    h = (interval_end - interval_begin) / step

    res = 0.0
    i = 0

    while i <= step:
        if i == 0 or i == step:
            res += func(interval_begin + i*h)
        elif i % 2 != 0: 
            res += 4 * func(interval_begin + i*h)
        else:
            res += 2 * func(interval_begin + i*h)

        # print( str(res) )
        i += 1

    # print('\n')
    return res * h/3


def calc_with_eps(eps: float, func,  interval_begin: float, interval_end: float):
    n = 10
    itegral = simpson_rule( lambda x:  func(x), interval_begin, interval_end, n )
    itegral_second = simpson_rule( lambda x: func(x), interval_begin, interval_end, n * 2 )

    d: float = abs( itegral - itegral_second )
    while d > eps:
        n *= 2
        itegral = simpson_rule( lambda x: func(x), interval_begin, interval_end, n )
        itegral_second = simpson_rule( lambda x: func(x), interval_begin, interval_end, n * 2 )

    return itegral
        

if __name__ == "__main__":
    eps = input("Input eps: ")
    print( "Your integral value: " + str(calc_with_eps(float(eps), lambda x: math.sin(x), 0, 1.57)) )