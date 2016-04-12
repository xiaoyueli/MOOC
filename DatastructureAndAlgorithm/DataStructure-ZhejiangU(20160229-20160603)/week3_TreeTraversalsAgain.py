def print_sequ(sequence):
    result = ""
    for item in sequence:
        result += str(item) + " "

    print(result[:-1])

def post_travel(pre, inorder, length):
    post = []
    if length:
        root = pre[0]
        for idx in range(length):
            if inorder[idx] == root:
                l_length = idx
                break
        r_length = length - 1 - l_length
        l_in = inorder[:l_length]
        r_in = inorder[l_length + 1:]
        l_pre = pre[1: 1 + l_length]
        r_pre = pre[l_length + 1:]

        new_l = post_travel(l_pre, l_in, l_length)
        new_r = post_travel(r_pre, r_in, r_length)

        post.extend(new_l)
        post.extend(new_r)
        post.append(root)

    return post

class Node:
    """docstring for Node"""
    def __init__(self, value, left_child = None, right_child = None):
        self._value = value
        self._left_child = left_child
        self._right_child = right_child

    def get_left_child(self):
        return self._left_child

    def set_left_child(self, left_child):
        self._left_child = left_child

    def get_right_child(self):
        return self._right_child

    def set_right_child(self, right_child):
        self._right_child = right_child

    def get_value(self):
        return self._value
        

def build_tree(pre, inorder, length):
    root = None
    if length:
        root = Node(pre[0])

        for idx in range(length):
            if inorder[idx] == root.get_value():
                l_length = idx
                break

        r_length = length - 1 - l_length
        l_pre = pre[1: 1 + l_length]
        r_pre = pre[l_length + 1:]
        l_in = inorder[: l_length]
        r_in = inorder[l_length + 1 :]

        l_child = build_tree(l_pre, l_in, l_length)
        r_child = build_tree(r_pre, r_in, r_length)

        root.set_left_child(l_child)
        root.set_right_child(r_child)

    return root
    
def post_tree_travel(tree):
    seq = ""
    l_seq = ""
    r_seq = ""
    if tree:
        root = tree.get_value()
        left_child = tree.get_left_child()
        right_child = tree.get_right_child()
        if left_child:
            l_seq = post_tree_travel(left_child) + " "
        if right_child:
            r_seq = post_tree_travel(right_child) + " "

        seq = l_seq + r_seq + str(root)

    return seq




def main():
    length = int(input())
    pre = []
    pre_temp = []
    inorder = []
    post = []

    for dummy in range(length * 2):
        info = input().split()
        if info[0] == "Push":
            pre.append(int(info[1]))
            pre_temp.append(int(info[1]))
        elif info[0] == "Pop":
            inorder.append(pre_temp.pop(-1))

    # the way without building a tree
    post = post_travel(pre, inorder, length)
    print_sequ(post)

    # the way with building a tree
    tree = build_tree(pre, inorder, length)
    print(post_tree_travel(tree))


    
main()

