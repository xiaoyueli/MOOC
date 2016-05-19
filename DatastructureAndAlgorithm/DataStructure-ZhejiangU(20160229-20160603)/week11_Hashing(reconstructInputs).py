"""
最大N随机错误
"""
def reconstruct(data):
    hash_pre = {}
    hash_next = {}
    for idx in range(len(data)):
        hash_next[data[idx]] = -1
        if data[idx] > -1:
            hashcode = data[idx] % len(data)
            if hashcode == idx:
                hash_pre[data[idx]] = -1                
            else:
                if idx == 0:
                    hash_pre[data[idx]] = data[len(data) - 1]
                    hash_next[data[len(data)- 1]] = data[idx]
                else:
                    hash_pre[data[idx]] = data[idx - 1]
                    hash_next[data[idx-1]] = data[idx]

    miniheap = []
    for item in data:
        if item > -1 and hash_pre[item] < 0:
            miniheap = insertMiniheap(miniheap, item)
            
    inputs = []
    while len(miniheap) > 1:
        smallest = deleteItem(miniheap, 1)
        inputs.append(smallest)
        if hash_next[smallest] >= 0:
            insertMiniheap(miniheap, hash_next[smallest])
    return inputs


def insertMiniheap(heap, item):
    
    if not heap:
        heap = [None]
    heap.append(item)
    idx = len(heap) - 1
    par_idx = idx // 2
    while par_idx > 0:
        if item < heap[par_idx]:
            heap[idx] = heap[par_idx]
            idx = par_idx
            par_idx = idx // 2
        else:
            break
    heap[idx] = item
    return heap

def deleteItem(heap, root):
    if len(heap) == 1 or root > len(heap) - 1:
        return None
    result = heap[root]
    last = heap.pop(-1)
    son_idx = 2 * root
    while son_idx <= len(heap) - 1:
        next_idx = son_idx
        if son_idx < len(heap) - 1 and heap[son_idx] > heap[son_idx + 1]:
            next_idx += 1
        if last > heap[next_idx]:
            heap[root] = heap[next_idx]
            root = next_idx
            son_idx = root * 2
        else:
            break
    if len(heap) > 1:
        heap[root] = last
    return result

def printOut(lst):
    result = ""

    for item in lst:
        result += str(item) + " "

    print(result[:-1])

def main():
    total = int(input())
    data = input().split()

    for idx in range(total):
        data[idx] = int(data[idx])

    inputs = reconstruct(data)

    printOut(inputs)

main()
