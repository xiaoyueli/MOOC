"""Optional program for lab02 """

from lab02 import *
import argparse

# Extra Question

def generator():
    """Generates an encrypter and decrypter.

    >>> e, d = generator()
    >>> msg = 'text'
    >>> encrypted = e(msg)
    >>> encrypted != msg
    True
    >>> decrypted = d(encrypted)
    >>> decrypted == msg
    True
    """
    "*** YOUR CODE HERE ***"
    caesar2 = caesar_generator(2, add)
    caesar3 = caesar_generator(3, add)
    decrys2 = caesar_generator(2, sub)
    decrys3 = caesar_generator(3, sub)

    return make_encrypter(caesar2, mirror_letter, caesar3), make_decrypter(decrys2, mirror_letter, decrys3) # Change this line
    
    """caesar2 = caesar_generator(2, add)
    caesar3 = caesar_generator(3, add)
    brutus2 = caesar_generator(2, sub)
    brutus3 = caesar_generator(3, sub)
    return make_encrypter(caesar2, mirror_letter, caesar3), \
        make_decrypter(brutus2, mirror_letter, brutus3)
    """
encryptor, decryptor = generator()


def count_cond(condition):
    """
    >>> count_factors = count_cond(lambda n, i: n % i == 0)
    >>> count_factors(2) # 1, 2
    2
    >>> count_factors(4) # 1, 2, 4
    3
    >>> count_factors(12) # 1, 2, 3, 4, 6, 12
    6

    >>> is_prime = lambda n, i: count_factors(i) == 2
    >>> count_primes = count_cond(is_prime)
    >>> count_primes(2) # 2
    1
    >>> count_primes(3) # 2, 3
    2
    >>> count_primes(4) # 2, 3
    2
    >>> count_primes(5) # 2, 3, 5
    3
    >>> count_primes(20) # 2, 3, 5, 7, 11, 13, 17, 19
    8
    """
    "*** YOUR CODE HERE ***"
    def cond( n):
        result = 0
        i = 1
        while i <= n:
            if condition(n, i):
                result += 1
            i += 1
        return result

    return cond


def cycle(f1, f2, f3):
    """ Returns a function that is itself a higher order function
    >>> def add1(x):
    ...     return x + 1
    >>> def times2(x):
    ...     return x * 2
    >>> def add3(x):
    ...     return x + 3
    >>> my_cycle = cycle(add1, times2, add3)
    >>> identity = my_cycle(0)
    >>> identity(5)
    5
    >>> add_one_then_double = my_cycle(2)
    >>> add_one_then_double(1)
    4
    >>> do_all_functions = my_cycle(3)
    >>> do_all_functions(2)
    9
    >>> do_more_than_a_cycle = my_cycle(4)
    >>> do_more_than_a_cycle(2)
    10
    >>> do_two_cycles = my_cycle(6)
    >>> do_two_cycles(1)
    19
    """
    "*** YOUR CODE HERE ***"
    def select_fun(g1, g2, g3, i):
        while i > 3:
            i = i - 3
        if i == 1:
            return g1
        elif i == 2:
            return g2
        elif i == 3:
            return g3
    def cyc(g1, g2, g3, n, x):
        if n == 1:
            return select_fun(g1, g2, g3, n)(x)
        else:
            return select_fun(g1, g2, g3, n)(cyc(g1, g2, g3, n-1, x))


    def fun1( n ):
        def fun2(x):
            if n == 0:
                result = x
            else:
                result = cyc(f1, f2, f3, n, x) 
            return result              
        return fun2
    return fun1

# You can ignore the rest of this file.

def parse_args():

    # define arguments for the file
    parser = argparse.ArgumentParser(
        description='Encrypt and decrypt source files')
    parser.add_argument('-s', '--source', type=str,
        help='the path to the source file')
    parser.add_argument('-o', '--output', type=str,
        help='the path to a new output file')
    parser.add_argument('command', choices=['encrypt', 'decrypt'],
        help='instruct program to "encrypt" or "decrypt"')

    # parse arguments
    args = parser.parse_args()

    return args


def run():
    """Run the encryption or decryption"""
    try:
        source, output = args.source, args.output
    except AttributeError:
        print('Required argument missing')
        return

    if source == output:
        print('ERROR: Source and output paths are identical!')
        return


    source_data = open(source).read()

    if args.command == 'encrypt':
        func = encryptor
        if not callable(func):
            print('ERROR: "%s" is not a function!' % str(encryptor))
            return
        output_data = func(source_data)
    elif args.command == 'decrypt':
        func = decryptor
        if not callable(func):
            print('ERROR: "%s" is not a function!' % str(decryptor))
            return
        output_data = func(source_data)

    open(output, 'w').write(output_data)

if __name__ == '__main__':

    args = parse_args()
    run()
