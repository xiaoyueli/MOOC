def inverse_cascade(n):
	grow(n)
	print(n)
	shrink(n)

def f_and_then(f, g, n):
	if n:
		f(n)
		g(n)
		
grow = lambda n: f_and_then(grow, print, n//10)
shrink = lambda n: f_and_then(print, shrink, n//10)