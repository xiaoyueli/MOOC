def initilize_point_info(num):

    earliest = {}
    pre_points = {}
    end_points = []
    for idx in range(num):
        earliest[idx] = 0
        pre_points[idx] = []
        end_points.append(idx)

    return earliest, pre_points, end_points

def check(pre_points, sta_p, end_p):
    pres = pre_points[end_p]
    for point in pres:
        if sta_p == point:
            return True
    
    return False

def remove_point(end_points, point):

    for item in end_points:
        if item == point:
            end_points.remove(item)
            break

def update_points_info(pre_points, end_points, sta_point, end_point):
    pre_points[end_point].append(sta_point)
    remove_point(end_points, sta_point)
    for pre_p in pre_points[sta_point]:
        pre_points[end_point].append(pre_p)

def get_earliest_time(ear_times, end_points):
    earliest_time = -1
    for point in end_points:
        temp = ear_times[point]
        if temp > earliest_time:
            earliest_time = temp

    return earliest_time

def read_activity_info(ori_info, num):
    ear_times = ori_info[0]
    pre_points = ori_info[1]
    end_points = ori_info[2]

    for dummy in range(num):
        info = input().split()
        sta_point = int(info[0])
        end_point= int(info[1])
        last_time = int(info[2])
        end_time = ear_times[sta_point] + last_time
        if end_time > ear_times[end_point]:
            ear_times[end_point] = end_time
        update_points_info(pre_points, end_points, sta_point, end_point)	
        if check(pre_points, end_point, sta_point):
            return -1
            
    
    earliest = get_earliest_time(ear_times, end_points) 
    return 	earliest


def main():
    info = input().split()
    points = int(info[0])
    edges = int(info[1])

    ori_info = initilize_point_info(points)

    earliest = read_activity_info(ori_info, edges)
    
    if earliest == -1:
        print("Impossible")
    else:
        print(earliest)

main()
