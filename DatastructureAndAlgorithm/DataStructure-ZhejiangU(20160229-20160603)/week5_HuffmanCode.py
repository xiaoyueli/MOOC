"""
运行超时
"""

class Node(object):
    """docstring for Node"""
    def __init__(self, char, fre, l_son = None, r_son = None):
        self._char = char
        self._fre = fre
        self._l_son = l_son
        self._r_son = r_son

    def __str__(self):
        if not self._char:
            self._char = "None"
        return self._char + "(" + str(self._fre) + ")" 
        
    def get_fre(self):
        return self._fre

    def get_char(self):
        return self._char

    def set_char(self, char):
        self._char = char

    def get_l_child(self):
        return self._l_son

    def set_l_child(self, son):
        self._l_son = son

    def get_r_child(self):
        return self._r_son

    def set_r_child(self, son):
        self._r_son = son

def build_miniheap(heap , node):
    if not heap:
        heap.append(None)

    heap.append(node)
    fre = node.get_fre()
    idx = len(heap) - 1
    parent_idx = idx // 2

    while parent_idx:
        if fre < heap[parent_idx].get_fre():
            heap[idx] = heap[parent_idx]
            idx = parent_idx
            parent_idx //= 2
        else:
            break
    heap[idx] = node

def delete_root(heap):
    """
    Return the root of the heap and remove it from the top
    Keep the left to be a heap still
    """
    if len(heap) == 1:
        return None
    elif len(heap) == 2:
        return heap.pop(1)
    else:
        root = heap[1]
        last = heap.pop(-1)
        root_idx = 1
        l_idx = root_idx * 2
        r_idx = root_idx * 2 + 1
        next_idx = l_idx
        

        while next_idx < len(heap):
            if r_idx < len(heap):
                if heap[l_idx].get_fre() < heap[r_idx].get_fre():
                    next_idx = l_idx
                else:
                    next_idx = r_idx
            elif l_idx < len(heap):
                next_idx = l_idx
            else:
                break

            if last.get_fre() > heap[next_idx].get_fre():
                heap[root_idx] = heap[next_idx]
                root_idx = next_idx
                l_idx = root_idx * 2
                r_idx = root_idx * 2 + 1
                next_idx = l_idx
            else:
                break

        heap[root_idx] = last

    return root


        
def build_huffman_tree(miniheap):
    """
    Build a huffman tree
    Return the root
    """
    new_heap = list(miniheap)
    while len(new_heap) > 2:
        min_fir = delete_root(new_heap)
        min_sec = delete_root(new_heap)
        new_node = Node(None, min_fir.get_fre() + min_sec.get_fre(), min_fir, min_sec)
        build_miniheap(new_heap, new_node)

    return new_heap[1]

def weigh_huffman_tree(tree, level):
    """
    Compute the weight of the tree
    """
    if tree.get_char():
        return tree.get_fre() * level
    else:
        return weigh_huffman_tree(tree.get_l_child(), level + 1) + \
        weigh_huffman_tree(tree.get_r_child(), level + 1)
    
def process_code(char, get_child, set_child, status, left_codes):
    """
    Check if the character alreadly exists as it should be
    Return the next node as a root
    """
    if not get_child():
        node = Node(char, 0)
        set_child(node)
        status.append(True)
        return node
    elif get_child().get_char() == char and left_codes != 0:
        status.append(True)
        return get_child()
    else:
        status.append(False)
        return None
        

def prefix_code(tree, case_codes):
    """
    Return True if the codes is not prefix codes, otherwise return False
    """
    if not tree:
        tree = Node(None, None)
    codes = list(case_codes)
    while codes:
        status = []
        code = codes.pop(0)
        if code == "0":
            tree = process_code("0", tree.get_l_child, tree.set_l_child, status, len(codes))
        else:
            tree = process_code("1", tree.get_r_child, tree.set_r_child, status, len(codes))

        if not status.pop(0):
            return False
    
    return True    

def main():
    total_char = int(input())
    char_fre_info = input().split()
    fre_info = {}
    miniheap = []
    for idx in range(total_char):
        char = char_fre_info[idx * 2]
        fre = int(char_fre_info[idx * 2 + 1])
        fre_info[char] = fre
        node = Node(char, fre)
        build_miniheap(miniheap, node)

    huff_tree = build_huffman_tree(miniheap)
    std_weight = weigh_huffman_tree(huff_tree, 0)

    total_case = int(input())

    for dummy in range(total_case):
        new_huff_tree = Node(None, None)
        weight = 0
        is_correct = True
        for idx in range(total_char):
            case_info = input().split()
            case_char = case_info[0]
            case_codes = case_info[1]
            if is_correct and prefix_code(new_huff_tree, case_codes):
                weight += fre_info[case_char] * len(case_codes)
            else:
                is_correct = False

        if weight == std_weight:
            print("Yes")
        else:
            print("No")
            
main()
