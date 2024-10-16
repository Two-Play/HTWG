import sympy as sp
import numpy as np
import matplotlib.pyplot as plt
import scipy.optimize as opt


def newton(f, Df, x0, epsilon, max_iter):
    xn = x0
    for n in range(0, max_iter):
        fxn = f(xn)
        if abs(fxn) < epsilon:
            print('Found solution after', n, 'iterations.')
            return xn
        Dfxn = Df(xn)
        if Dfxn == 0:
            print('Zero derivative. No solution found.')
            return None
        xn = xn - fxn / Dfxn
    print('Exceeded maximum iterations. No solution found.')
    return None


def newtin2(func, x, n):
    def f(x):
        return eval(func)

    def df(x):
        return np.diff(f(x))

    for i in range(1, n):
        x = x - f(x) / df(x)
    print("Nullstelle:", x)


def newt3(func, dx, x, n):
    def f(x):
        return eval(func)

    def df(x):
        return eval(dx)

    for i in range(1, n):
        x = x - f(x) / df(x)
    print("Nullstelle:", x)



# Symbole definieren
A, B, C, D, E, y = sp.symbols('A B C D E y')

# Funktion t(y)
# t = 1 / D * sp.sqrt(C ** 2 + y ** 2) + 1 / E * sp.sqrt((B - y) ** 2 + A ** 2)
t = 1 / D * sp.sqrt(np.power(C, 2) + np.power(y, 2)) + 1 / E * sp.sqrt(np.power((B - y), 2) + np.power(A, 2))
# Ableitung von t(y) nach y
dxt = sp.diff(t, y)

# Numerische Auswertung der Funktionen für bestimmte Parameterwerte
A_val = 100
B_val = 100
C_val = 100
D_val = 5
E_val = 3

# Lambda-Funktionen für t(y) und dxt(y)
t_numeric = sp.lambdify(y, t.subs([(A, A_val), (B, B_val), (C, C_val), (D, D_val), (E, E_val)]), modules="numpy")
dxt_numeric = sp.lambdify(y, dxt.subs([(A, A_val), (B, B_val), (C, C_val), (D, D_val), (E, E_val)]), modules="numpy")

# Werte für y erstellen
y_vals = np.linspace(-25, 100, 500)

# Berechnung der Funktionen für die gegebenen Parameter
t_values = t_numeric(y_vals)
dxt_values = dxt_numeric(y_vals)

# Vernünftiger Startwert für das Newton-Verfahren
start_value = np.ndarray(50)

# Nullstellensuche mit Newton-Verfahren
# result = root(t, np.ndarray(A / 2), jac=dxt)
# optimal_position = result.x

# print("Optimale Position des Rettungsschwimmers:", opt.newton(t_numeric, A / 2, fprime=dxt_numeric, tol=1.48e-08, maxiter=50, full_output=False, disp=True))

# Nullstellensuche mit Newton-Verfahren
# newtin2("1 / 5 * sqrt(100 ** 2 + y ** 2) + 1 / 3 * sqrt((100 - y) ** 2 + 100 ** 2", y, 50)

newt3("x**2 - 2", "2*x", 2, 10)

# Graphen erstellen
plt.figure(figsize=(10, 6))

# Funktion t(y)
plt.subplot(2, 1, 1)
plt.plot(y_vals, t_values, label='t(y)')
plt.ylabel('Gesamtzeit t')
plt.xlabel('Eintrittspunkt ins Wasser y')
plt.title('Funktion t(y)')
plt.legend()
plt.grid()

# Ableitung dxt(y)
plt.subplot(2, 1, 2)
plt.plot(y_vals, dxt_values, label="t'(y)")
plt.xlabel('y')
plt.ylabel("t'(y)")
plt.title("Ableitung von t(y) nach y")
plt.legend()
plt.grid()

plt.tight_layout()
plt.show()
