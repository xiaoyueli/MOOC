
def initilize_point_info(num):
    earliest = {}
    pre_points = {}
    end_points = []
    sta_points = []
    latest = {}
    post_points = {}
    for idx in range(1, num + 1):
        earliest[idx] = -1
        pre_points[idx] = []
        end_points.append(idx)
        sta_points.append(idx)
        latest[idx] = -1
        post_points[idx] = []

    return earliest, pre_points, end_points, sta_points, latest, post_points

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

def update_points_info(pre_points, sta_points, end_points, sta_point, end_point, last_time, post_points):
    pre_points[end_point].append([sta_point, last_time])
    post_points[sta_point].insert(0, end_point)
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
    late_times = act_info[4]
    
    earliest = -1
    key_points = []
    for point in end_points:
        ear_times[point] = compute_earliest_time(pre_points, ear_times, point)
        if earliest < ear_times[point]:
            earliest = ear_times[point]
            key_points = [point]
        elif earliest == ear_times[point]:
            key_points.append(point)
    
    for key_p in key_points:
        late_times[key_p] = earliest
    return earliest

def read_activity_info(ori_info, num):
    ear_times = ori_info[0]
    pre_points = ori_info[1]
    end_points = ori_info[2]
    sta_points = ori_info[3]
    post_points = ori_info[5]

    for dummy in range(num):
        info = input().split()
        sta_point = int(info[0])
        end_point = int(info[1])
        last_time = int(info[2])
        update_points_info(pre_points, sta_points, end_points, \
            sta_point, end_point, last_time, post_points) 
        if check(pre_points, end_point, sta_point):
            return -1

    for point in sta_points:
        ear_times[point] = 0
        
            
    return  ori_info

def compute_latest_time(pre_points, ear_times, late_times, point):
    pres = pre_points[point]

    if not pres:
        late_times[point] = 0
        return True
    else:
        flag = False
        for pre_info in pres:
            pre_p = pre_info[0]
            last_time = pre_info[1]
            if ear_times[pre_p] + last_time == late_times[point]:
                late_times[pre_p] = late_times[point] - last_time
                if compute_latest_time(pre_points, ear_times, late_times, pre_p):
                    flag = True
                else:
                    late_times[pre_p] = -1
        return flag


def get_key_road(act_info):
    ear_times = act_info[0]
    pre_points = act_info[1]
    end_points = act_info[2]
    late_times = act_info[4]
    post_points = act_info[5]


    for point in end_points:
        if late_times[point] > 0:
            compute_latest_time(pre_points, ear_times, late_times, point)

    for point in range(1, len(pre_points)):
        if late_times[point] >= 0:
            nexts = post_points[point]
            for next_p in nexts:
                if late_times[next_p] > 0:
                    print(str(point) + "->" + str(next_p))


def main():
    info = input().split()
    points = int(info[0])
    activities = int(info[1])

    ori_info = initilize_point_info(points)

    act_info = read_activity_info(ori_info, activities)
    
    if act_info == -1:
        print(0)
    else:
        earliest = get_earliest_time(act_info, points)
        print(earliest)

        get_key_road(act_info)

main()


