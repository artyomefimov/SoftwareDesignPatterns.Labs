import matplotlib.pyplot as plot
import numpy as np
import sys

args_x = sys.argv[1].split(',')
args_y = sys.argv[2].split(',')

float_x = np.array(args_x).astype(np.float)
float_y = np.array(args_y).astype(np.float)

plot.plot(float_x, float_y)
plot.savefig('C:/Univer/Patterns/Labs/Labs/src/lab4/mvc/view/graphic/graphic.png', bbox_inches='tight')