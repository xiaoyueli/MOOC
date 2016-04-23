def initilize_point_info(num):
    earliest = {}
    pre_points = {}
    end_points = []
    sta_points = []
    for idx in range(num):
        earliest[idx] = -1
        pre_points[idx] = []
        end_points.append(idx)
        sta_points.append(idx)

    return earliest, pre_points, end_points, sta_points

def check(pre_points, sta_p, end_p):
    pres = pre_points[end_p]
    for point in pres:
        if point[0] == sta_p:
            return True
        else:
            if check(pre_points, sta_p, point[0]):
                return True
    return False

def remove_point(points, point):

    for item in points:
        if item == point:
            points.remove(item)
            break

def update_points_info(pre_points, sta_points, end_points, sta_point, end_point, last_time):
    pre_points[end_point].append([sta_point, last_time])
    remove_point(end_points, sta_point)
    remove_point(sta_points, end_point)


def compute_earliest_time(pre_points, ear_times, point):
    pres = pre_points[point]

    earliest = -1
    for pre_info in pres:
        pre_p = pre_info[0]
        last_time = pre_info[1]
        if ear_times[pre_p] < 0:
            ear_times[pre_p] = compute_earliest_time(pre_points, ear_times, pre_p)
        if earliest < ear_times[pre_p] + last_time:
            earliest = ear_times[pre_p] + last_time

    return earliest
                        

def get_earliest_time(act_info, num):
    ear_times = act_info[0]
    pre_points = act_info[1]
    end_points = act_info[2]
    
    earliest = -1
    for point in end_points:
        ear_times[point] = compute_earliest_time(pre_points, ear_times, point)
        if earliest < ear_times[point]:
            earliest = ear_times[point]
    return earliest

def read_activity_info(ori_info, num):
    ear_times = ori_info[0]
    pre_points = ori_info[1]
    end_points = ori_info[2]
    sta_points = ori_info[3]

    for dummy in range(num):
        info = input().split()
        sta_point = int(info[0])
        end_point= int(info[1])
        last_time = int(info[2])
        update_points_info(pre_points, sta_points, end_points, sta_point, end_point, last_time) 
        if check(pre_points, end_point, sta_point):
            return -1

    for point in sta_points:
        ear_times[point] = 0
                
    return  ori_info


def main():
    info = input().split()
    points = int(info[0])
    edges = int(info[1])

    ori_info = initilize_point_info(points)

    act_info = read_activity_info(ori_info, edges)
    
    if act_info == -1:
        print("Impossible")
    else:
        earliest = get_earliest_time(act_info, points)
        print(earliest)

main()
