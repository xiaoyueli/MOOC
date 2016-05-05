# Sort with swap(0, N)

def sortWithSwap(lst):
    visited = [False] * len(lst)

    cnt = 0
    for idx in range(len(lst)):
        flag = False
        if not visited[idx]:
            cur = idx
            while not visited[cur]:
                next_idx = lst[cur]
                visited[cur] = True
                cur = next_idx
                if not visited[cur]:
                    cnt += 1
                    flag = True
            if flag and idx != 0:
                cnt += 2

    return cnt

def main():
    total = int(input())
    numbers = input().split()


    for idx in range(total):
        numbers[idx] = int(numbers[idx])


    times = sortWithSwap(numbers)

    print(times)

main()

