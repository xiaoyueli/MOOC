#Q1.6.1
def fizzbuzz(n) :
	x =1
	while x != n+1:
		if x % 3 ==0 and x % 5 ==0:
			print("fizzbuzz")
		elif x % 3 ==0:
			print("fizz")
		elif x % 5 ==0:
			print("buzz")
		else:
			print(x)
		x += 1
#Q1.6.2
def choose(n, k):
	"""Returns the number of ways to choose K items from N items.
	>>> choose(5, 2)
	10
	>>> choose(20, 6)
	38760
	
	#This is my way.
	total=1
	cnt = k
	cnt2= 1
	while cnt != 0:
		total= total * n
		n -= 1
		cnt2 *= cnt
		cnt -= 1
	return total // cnt2
	"""
	def product(m, n):
		total=1
		while m != n - 1:
			total = total * m
			m -= 1
		return total

	return product(n, n-k+1) // product(k, 1)

#Q2.2
def keep_ints(cond, n):
	"""Print out all integers 1..i..n where cond(i) is true

	>>>def is_even(x):
			#Even numbers have remainder 0 when divided by 2.
			return x % 2 == 0
	>>>keep_ints(is_even, 5)
	2
	4
	"""
	
	while n != 0:
		if cond(n):
			print(n)
		n -= 1

def is_prime(x):
		"""Prime numbers are dividable only by 1 and itself.
		"""
		prime = True
		if x == 1:
			prime = False
		for i in range(2, x):
			if x % i ==0:
				prime = False
				
		return prime

#Q2.4.2
def keep_ints2(n):
	"""Returns a function which takes one parameter cond and prints out all integers 1..i..n where calling cond(i) returns True.

	>>>def is_even(x):
			#Even numbers have remainder 0 when divided by 2.
			return x % 2 == 0
	>>>keep_ints(is_even, 5)
	2
	4

	#Solution:
	def do_keep(cond):
		i = 1
		while i<=n:
			if cond(i):
				print(i)
			i += 1
	return do_keep
	"""
	def afunc(cond):
		i = n
		while i != 0:
			if cond(i):
				print(i)
			is_prime -= 1
	return afunc
	

			


