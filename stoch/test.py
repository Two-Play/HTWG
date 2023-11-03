import numpy
import statistics

urliste = [14, 24, 22, 19, 18, 36, 15, 29, 41, 17]

mittelwert = numpy.mean(urliste)
quartiele = statistics.quantiles(urliste, n=4)
quantiele = numpy.quantile(urliste)

print("Mittelwert:", mittelwert)
print("Quartiele:", quartiele)
print("Quantiele:", quantiele)
