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
                break
        l_length = idx
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

    post = post_travel(pre, inorder, length)

    print_sequ(post)
    
main()

