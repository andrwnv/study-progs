import sys, math

# default func: f(x) = 4x + e^x


def res_of_default_function(arg: float) -> float:
    """
        calculate f(x) = 4x + e^x
    """

    return (4 * arg) + math.exp(arg)


def secant_method(first_arg: float, second_arg: float, eps: float) -> float:
    """
        args:
            first_arg  - begin of interval
            second_arg - end of interval
            eps        - precision
    """

    # calc default func res by first & second args.
    m_x0 = res_of_default_function(first_arg)
    m_x1 = res_of_default_function(second_arg)
    
    x = 0
    iterator = 0
    
    while math.fabs(m_x1) > eps:
        try:
            # calc denominator.
            delta_x = (float)(m_x1 - m_x0) / (float)(second_arg - first_arg)
            x = second_arg - (float)(m_x1 / delta_x)
            print(str(x) + "   iteration: " + str(iterator) + "\n")

        except ZeroDivisionError: 
            print("Failed! Delta X is zero!")
            sys.exit(2)

        iterator += 1
        first_arg, second_arg = second_arg, x
        m_x0, m_x1 = m_x1, res_of_default_function(second_arg)

    return x


if __name__ == "__main__":

    eps = input('Enter eps: ')
    a = input('Enter begin of interval: ')
    b = input('Enter end of interval: ')

    result: float = secant_method(float(a), float(b), float(eps))
    
    print("Result: " + str(result) + "\n")
    print(str(res_of_default_function(result)) + '\n')