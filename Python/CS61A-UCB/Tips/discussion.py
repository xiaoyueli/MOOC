def countdown(n):
	if n == 0:
		return
	else:
		print(n)
		countdown(n-1)


def countup(n):
	if n==0:
		return
	else:
		countup(n-1)
		print(n)

def multiply(m, n):
	if n == 1:
		result = m
	else:
		result = m + multiply(m, n-1)

	return result

def sum_digits(n):
	if n < 10:
		sum = n
	else:
		sum = n % 10 + sum_digits(n // 10)
	return sum