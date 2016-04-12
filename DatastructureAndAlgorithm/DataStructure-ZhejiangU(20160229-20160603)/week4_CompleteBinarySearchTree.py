import math

def print_seq(seq):
    result = ""
    for item in seq:
        result += str(item) + " "

    print(result[:-1])

def get_lchild_num(seq):
    length = len(seq)
    height = math.floor(math.log(length + 1, 2))
    before_last_level = math.pow(2, height) - 1
    last_level = length - before_last_level
    last_level_full = math.pow(2, height)
    if last_level > last_level_full // 2:
        last_level = last_level_full // 2
        
    left_child = math.pow(2, height - 1) - 1 + last_level

    
    return int(left_child)

def completebinarytree(seq, cbt, root_idx):

    if seq:
        left_num = get_lchild_num(seq)
        left_seq = seq[:left_num]
        right_seq = seq[left_num + 1:]

        cbt[root_idx] = seq[left_num]

        completebinarytree(left_seq, cbt, root_idx * 2 + 1)
        completebinarytree(right_seq, cbt, root_idx * 2 + 2)

def main():
    total = int(input())
    info = input().split()

    seq = []
    cbt_seq = []
    for item in info:
        seq.append(int(item))
        cbt_seq.append(None)

    seq = sorted(seq)

    completebinarytree(seq, cbt_seq, 0)

    print_seq(cbt_seq)
    
main()