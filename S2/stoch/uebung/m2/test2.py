import sympy as sym
import numpy as np
import math

y, x, A, B, C, D, E = sym.symbols('y x A B C D E')

t1 = np.e**(x-2)**2
t2 = math.sqrt((B - y) ** 2 + A ** 2)
f = 1 / D * t1 + 1 / E * t2

diff_f = sym.diff(f, x)
diff_f

f_func = sym.lambdify(x, f, 'numpy')
diff_f_func = sym.lambdify(x, diff_f, 'numpy')


def newtonMethod(x0, iterationNumber, f, df):
    x = x0

    for i in range(iterationNumber):
        x = x - f(x) / df(x)

    residual = np.abs(f(x))
    return x, residual


solution, residual = newtonMethod(-2, 200, f_func, diff_f_func)
print("Solution: ", solution)