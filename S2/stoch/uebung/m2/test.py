# Newtwon method implementation in python
from typing import Any
import sympy as sp
import numpy as np

y, x, A, B, C, D, E = sp.symbols('y x A B C D E')

# Numerische Auswertung der Funktionen für bestimmte Parameterwerte
A_val = 100
B_val = 100
C_val = 100
D_val = 5
E_val = 3

f = np.e**(x-2)**2
#t: float | Any = 1 / D * sp.sqrt(C ** 2 + y ** 2) + 1 / E * sp.sqrt((B - y) ** 2 + A ** 2)
#f = (1 / 5 * sp.sqrt(100 ** 2 + y ** 2) + 1 / 3 * sp.sqrt((100 - y) ** 2 + 100 ** 2))

# Lambda-Funktionen für t(y) und dxt(y)
# t_numeric = sp.lambdify(y, t.subs([(A, A_val), (B, B_val), (C, C_val), (D, D_val), (E, E_val)]), modules="numpy")


diff_f = sp.diff(f, x)
diff_f


# f_func = sp.lambdify(x, t, 'numpy')
# diff_f_func = sp.lambdify(x, diff_f, 'numpy')


def newtonMethod(x0, iterationNumber, f, df):
    x = x0

    for i in range(iterationNumber):
        x = x - f(x) / df(x)

    residual = np.abs(f(x))
    return x, residual


solution, residual = newtonMethod(-2, 200, f, diff_f)

# print("Solution: ", solution)
