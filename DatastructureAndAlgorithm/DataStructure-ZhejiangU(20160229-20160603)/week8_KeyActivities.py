def compute_key_road(end_p, pre_points, earlist_time):

    pres = pre_points[end_p]
    if not pres:
        return True
    earlist_t = earlist_time[end_p]
    flag = False
    for p_info in pres:
        point =p_info[0]
        last_t = p_info[1]
        latest_t = earlist_t - last_t
        if latest_t == earlist_time[point] and compute_key_road(point, pre_points, earlist_time):
            print(str(point) + "->" + str(end_p))
            flag = True

    return flag
            

def get_earliest_time(earlist_time, end_points):
    ear_time = 0

    for point in end_points:
        temp = earlist_time[point]
        if temp > ear_time:
            ear_time = temp
            end_p = point

    return end_p, ear_time

def check_pre_point(pre_points, sta_p, end_p):
    pres = pre_points[end_p]

    for point in pres:
        if sta_p == point[0]:
            return True

    return False

def remove_point(end_points, sta_p):
    
    for point in end_points:
        if sta_p == point:
            end_points.remove(point)
            break

def update_point_info(pre_points, dir_pre_points, end_points, sta_p, end_p, last_time):
    pre_points[end_p].append([sta_p, last_time])
    dir_pre_points[end_p].insert(0, [sta_p, last_time])
    remove_point(end_points, sta_p)

    pres = pre_points[sta_p]
    for point in pres:
        pre_points[end_p].append(point)

def printout_key_road(key_road):
    key_road.sort()
    roads = set(key_road)
    for item in roads:
        print(str(item[0]) + "->" + str(item[1]))

def read_activity_info(info, num):
    pre_points = info[0]
    dir_pre_points = info[1]
    earlist_time = info[2]
    end_points = info[3]

    for dummy in range(num):
        act_info = input().split()
        sta_p = int(act_info[0])
        end_p = int(act_info[1])
        last_time = int(act_info[2])
        new_ear_time = earlist_time[sta_p] + last_time
        if new_ear_time > earlist_time[end_p]:
            earlist_time[end_p] = new_ear_time
        update_point_info(pre_points, dir_pre_points, end_points, sta_p, end_p, last_time)

        if check_pre_point(pre_points, end_p, sta_p):
            print(0)
            return

    earlist_time_info = get_earliest_time(earlist_time, end_points)
    print(earlist_time_info[1])
    compute_key_road(earlist_time_info[0], dir_pre_points, earlist_time)

def initilize_points(num):
    pre_points = {}
    dir_pre_points = {}
    earlist_time = {}
    end_points = []

    for idx in range(1, num + 1):
        pre_points[idx] = []
        dir_pre_points[idx] = []
        earlist_time[idx] = 0
        end_points.append(idx)

    return pre_points, dir_pre_points, earlist_time, end_points

def main():
    info = input().split()
    points = int(info[0])
    activities = int(info[1])

    ori_info = initilize_points(points) 
    read_activity_info(ori_info, activities)

main()
