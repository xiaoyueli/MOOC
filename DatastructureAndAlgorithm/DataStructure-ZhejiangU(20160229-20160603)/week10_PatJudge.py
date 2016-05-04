#超时
class Student:
    """docstring for student"""
    def __init__(self, stu_id, problems, full_scores):
        self._id = stu_id
        self._scores = [-1] * (problems + 1)
        self._full_score_number = 0
        self._full_scores = [0] + full_scores
        
        
    def __str__(self):
        result = self._id + " " + str(self._scores[0])
        for score in self._scores[1:]:
            if score >= 0:
                result += " " + str(score)
            else:
                result += " -"
        return result

    def updateScore(self, pro_id, score):
        
        
        if self._scores[pro_id] != self._full_scores[pro_id] and score == self._full_scores[pro_id]:
            self._full_score_number += 1
        if score == -1:
            self._scores[pro_id] = 0
        elif self._scores[0] == -1:
            self._scores[0] = 0
        if self._scores[pro_id] == -1:
            self._scores[pro_id] = score
            self._scores[0] += score
        elif self._scores[pro_id] < score:
            self._scores[0] += score - self._scores[pro_id]
            self._scores[pro_id] = score

        

    def getTScore(self):
        return self._scores[0]

    def getUserID(self):
        return self._id

    def getFSNumber(self):
        return self._full_score_number

    def getScores(self):
        return self._scores[1:]

def created(created_stu, stu_id):
    for item in created_stu:
        if item == stu_id:
            return True
    return False
        
def readInfo(problems, submittions, full_scores):
    students = {}
    created_stu = set([])
    for dummy in range(submittions):
        case = input().split()
        user_id = case[0]
        pro_id = int(case[1])
        score = int(case[2])

        if not created(created_stu, user_id):
            students[user_id] = Student(user_id, problems, full_scores)
            created_stu.add(user_id)
        students[user_id].updateScore(pro_id, score)

    lst = []
    for key in students.keys():
        lst.append(students[key])

    return lst

def subMerge(lst, temp, fir, sec, sec_end):
    idx_one = fir
    idx_two = sec
    idx = fir

    while idx_one < sec and idx_two <= sec_end:

        if lst[idx_one].getTScore() > lst[idx_two].getTScore():
            temp[idx] = lst[idx_one]
            idx_one += 1
        elif lst[idx_two].getTScore() > lst[idx_one].getTScore():
            temp[idx] = lst[idx_two]
            idx_two += 1
        else:
            if lst[idx_one].getFSNumber() > lst[idx_two].getFSNumber():
                temp[idx] = lst[idx_one]
                idx_one += 1
            elif lst[idx_two].getFSNumber() > lst[idx_one].getFSNumber():
                temp[idx] = lst[idx_two]
                idx_two += 1
            else:
                if int(lst[idx_one].getUserID()) < int(lst[idx_two].getUserID()):
                    temp[idx] = lst[idx_one]
                    idx_one += 1
                else:
                    temp[idx] = lst[idx_two]
                    idx_two += 1
        idx += 1

    while idx_one < sec:
        temp[idx] = lst[idx_one]
        idx += 1
        idx_one += 1

    while  idx_two <= sec_end:
        temp[idx] = lst[idx_two]
        idx += 1
        idx_two += 1
        

def merge(lst, temp, sub_len):
    
    idx = 0
    while idx < len(lst) - 2 * sub_len:
        subMerge(lst, temp, idx, idx + sub_len, idx + 2 * sub_len - 1)
        idx += 2 * sub_len

    if idx + sub_len < len(lst):
        subMerge(lst, temp, idx, idx + sub_len, len(lst) - 1)
    else:
        while idx < len(lst):
            temp[idx] = lst[idx]
            idx += 1

    return temp
        

def sort(students):
    length = len(students)
    sub_len = 1
    temp = [None] * length
    while sub_len < length:
        merge(students, temp, sub_len)
        sub_len *= 2
        merge(temp, students, sub_len)
        sub_len *= 2

    return students


def printOut(students):
    rank = 0
    last_score = -1
    cnt_same = 1
    while students:
        stu = students.pop(0)
        if stu.getTScore() < 0:
            break
            
        if stu.getTScore() != last_score:
            rank += cnt_same
            last_score = stu.getTScore()
            cnt_same = 1
        else:
            cnt_same += 1
            

        print(str(rank) + " " + str(stu))


def main():
    info = input().split()
    problems = int(info[1])
    submittions = int(info[2])

    full_score_list = input().split()


    for idx in range(len(full_score_list)):
        full_score_list[idx] = int(full_score_list[idx])

    students = readInfo(problems, submittions, full_score_list)
    

    sorted_students = sort(students)
    

    printOut(sorted_students)

main()

