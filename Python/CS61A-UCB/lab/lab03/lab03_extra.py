from lab03 import *

# Q10
def summation(n, term):
    """Return the sum of the first n terms of a sequence.

    >>> summation(5, lambda x: pow(x, 3))
    225
    """
    total, k = 0, 1
    while k <= n:
        total, k = total + term(k), k + 1
    return total

def interleaved_sum(n, odd_term, even_term):
    """Compute the sum odd_term(1) + even_term(2) + odd_term(3) + ..., up
    to n.

    >>> # 1 + 2^2 + 3 + 4^2 + 5
    ... interleaved_sum(5, lambda x: x, lambda x: x*x)
    29
    """
    "*** YOUR CODE HERE ***"

    def even_or_odd(x):
        if x == 0:
            return even_term
        elif x == 1:
            return odd_term
        else:
            return even_or_odd(x-2)

    if n == 0:
        result = even_term(n)
    elif n == 1:
        result = odd_term(n)
    else:
        result = even_or_odd(n)(n) + even_or_odd(n-1)(n-1) + interleaved_sum(n-2, odd_term, even_term)

    return result