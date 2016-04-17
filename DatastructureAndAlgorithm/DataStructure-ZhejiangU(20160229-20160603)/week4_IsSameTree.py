class Node:
    """docstring for Node"""
    def __init__(self, value, l_son = None, r_son = None, visited = False):
        self._value = value
        self._l_son = l_son
        self._r_son = r_son
        self._visited = visited

    def get_value(self):
        return self._value

    def get_lson(self):
        return self._l_son

    def set_lson(self, son):
        self._l_son = son

    def get_rson(self):
        return self._r_son

    def set_rson(self, son):
        self._r_son = son

    def get_visited(self):
        return self._visited

    def set_visited(self, value):
        self._visited = value


def check_node(tree, value):

    if value > tree.get_value():
        rson = tree.get_rson()
        if rson and not rson.get_visited():
            rson.set_visited(True)
            if rson.get_value() == value:
                return True
            else:
                return False
        elif rson:
            return check_node(rson, value)
        else:
            return False

    elif value < tree.get_value():
        lson = tree.get_lson()
        if lson and not lson.get_visited():
            lson.set_visited(True)
            if lson.get_value() == value:
                return True
            else:
                return False
        elif lson:
            return check_node(lson, value)
        else:
            return False

def is_same_bt(proto, seq):
    if not seq:
        return False
    root = int(seq.pop(0))
    if root != proto.get_value():
        return False
    proto.set_visited(True)
    while seq:
        value = int(seq.pop(0))
        if not check_node(proto, value):
            return False

    return True


def insert_node(root, value):
    if value < root.get_value():
        lson = root.get_lson()
        if lson:
            insert_node(lson, value)
        else:
            root.set_lson(Node(value))
    elif value > root.get_value():
        rson = root.get_rson()
        if rson:
            insert_node(rson, value)
        else:
            root.set_rson(Node(value))

def build_binary_tree(seq):
    if not seq:
        return None

    root = Node(int(seq.pop(0)))

    while seq:
        value = int(seq.pop(0))
        insert_node(root, value)

    return root

def main():
    info = input()
    while info != "0":

        case_num = int(info.split()[1])

        proto = input().split()

        for dummy in range(case_num):
            pro_t = build_binary_tree(list(proto))
            tree = input().split()
            if is_same_bt(pro_t, tree):
                print("Yes")
            else:
                print("No")

        info = input()

main()