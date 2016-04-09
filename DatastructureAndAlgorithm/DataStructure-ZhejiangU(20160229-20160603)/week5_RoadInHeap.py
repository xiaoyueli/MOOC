def insert_item(heap, value):
    
    if not heap:
        heap.append(value)
        return heap
    else:
        heap.append(value)
        idx = len(heap)
        heap.insert(0, None)
        parent = idx // 2
        if value < heap[parent]:
            while parent != 0 and value < heap[parent]:
                heap[idx] = heap[parent]
                idx = parent
                parent //= 2
          
            heap[idx] = value

    return heap[1:] 

def printRoad(heap, idx):
    heap = list(heap)
    heap.insert(0, None)
    result = []
    while idx != 0:
        result.append(str(heap[idx]))
        idx //=2

    print(" ".join(result))



def main():
    heap = []
    items = input().split()
    indices = input().split()
    for item in items:
        heap = insert_item(heap, int(item))

    for idx in indices:
        printRoad(heap, int(idx))
        
main()