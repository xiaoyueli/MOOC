"""
Student code for Word Wrangler game
"""

import urllib2
import codeskulptor
import poc_wrangler_provided as provided

WORDFILE = "assets_scrabble_words3.txt"


# Functions to manipulate ordered word lists

def remove_duplicates(list1):
    """
    Eliminate duplicates in a sorted list.

    Returns a new sorted list with the same elements in list1, but
    with no duplicates.

    This function can be iterative.
    """
    new_lst = []
    for item in list1:
        if new_lst.count(item) < 1:
            new_lst.append(item)
    return new_lst

def intersect(list1, list2):
    """
    Compute the intersection of two sorted lists.

    Returns a new sorted list containing only elements that are in
    both list1 and list2.

    This function can be iterative.
    """
    new_lst = []
    for item in list1:
        if list1.count(item) <= list2.count(item):
            new_lst.append(item)
    return new_lst

# Functions to perform merge sort

def merge(list1, list2):
    """
    Merge two sorted lists.

    Returns a new sorted list containing those elements that are in
    either list1 or list2.

    This function can be iterative.
    """ 
    lst1 = remove_duplicates(list1)
    lst2 = remove_duplicates(list2)
    new_lst = []
    while lst1 != [] and lst2 != []:
        if lst1[0] > lst2[0]:
            new_lst.append(lst2.pop(0))           
        elif lst1[0] < lst2[0]:
            new_lst.append(lst1.pop(0))
        else:
            new_lst.append(lst1.pop(0))
            new_lst.append(lst2.pop(0))
    new_lst.extend(lst1)
    new_lst.extend(lst2)
    return new_lst
                
def merge_sort(list1):
    """
    Sort the elements of list1.

    Return a new sorted list with the same elements as list1.

    This function should be recursive.
    """
    if list1 == []:
        return list1
    else:
        pivot = list1[0]
        lesser = [num for num in list1 if num < pivot]
        pivots = [num for num in list1 if num == pivot]
        greater = [num for num in list1 if num > pivot]
        return merge_sort(lesser) + pivots + merge_sort(greater)


# Function to generate all strings for the word wrangler game

def gen_all_strings(word):
    """
    Generate all strings that can be composed from the letters in word
    in any order.

    Returns a list of all strings that can be formed from the letters
    in word.

    This function should be recursive.
    """
    if len(word) == 0:
        return [""]
    else:
        first = word[0]
        lst = gen_all_strings(word[1:])
        for item in list(lst):
            for idx in range(len(item) + 1):
                lst.append(item[:idx] + first + item[idx:])
        return lst

# Function to load words from a file

def load_words(filename):
    """
    Load word list from the file named filename.

    Returns a list of strings.
    """
    words = []
    url = codeskulptor.file2url(WORDFILE)
    dic = urllib2.urlopen(url)
    words = [word[:-1] for word in dic.readlines()] 
    return words

def run():
    """
    Run game.
    """
    words = load_words(WORDFILE)
    wrangler = provided.WordWrangler(words, remove_duplicates, 
                                     intersect, merge_sort, 
                                     gen_all_strings)
    provided.run_game(wrangler)

# Uncomment when you are ready to try the game
#run()

    