# template for "Stopwatch: The Game"

import simplegui

# define global variables
cur_time = 0
cnt_stop = 0
cnt_win = 0
TIME_LIMITED = 6000

# define helper function format that converts time
# in tenths of seconds into formatted string A:BC.D
def format(time):
    """
    Convert current time in tenths of seconds into formatted String A:BC.D
    """
    tenthseconds = time % 10
    t_seconds = time / 10
    seconds = t_seconds % 60
    minutes = t_seconds / 60
    if seconds < 10:
        str_seconds = str(0) + str(seconds)
    else:
        str_seconds = str(seconds)
    return str(minutes) + ':' + str_seconds + '.' + str(tenthseconds)
    
# define event handlers for buttons; "Start", "Stop", "Reset"
def start_handler():
    """
    Start the timer
    """
    if cur_time == TIME_LIMITED:
        TIMER.stop()
    else:
        TIMER.start()

def stop_handler():
    """
    Stop the timer after counting the total times the stop button has been pressed 
    and those been pressed on the whole seconds when the timer is running 
    """
    global cnt_stop, cnt_win
    if TIMER.is_running():
        cnt_stop += 1
        if cur_time % 10 == 0:
            cnt_win += 1
    TIMER.stop()

def reset_handler():
    """
    Reset the program to an initial status
    """
    global cur_time, cnt_win, cnt_stop
    TIMER.stop()
    cur_time = 0
    cnt_win = 0
    cnt_stop = 0

# define event handler for timer with 0.1 sec interval
def timer_handler():
    """
    Count time in tenths of seconds
    """
    global cur_time
    cur_time += 1
    if cur_time == TIME_LIMITED:
        TIMER.stop()
    
# define draw handler
def draw_handler(canvas):
    """
    Draw the Stopwatch and counter 
    """
    canvas.draw_text(format(cur_time), [60, 100], 30, 'white')
    canvas.draw_text(str(cnt_win)+'/'+str(cnt_stop), [155, 25], 20, 'white')

# create frame
FRAME = simplegui.create_frame('Stopwatch', 200, 200, 100)
TIMER = simplegui.create_timer(100, timer_handler)

# register event handlers
FRAME.set_draw_handler(draw_handler)
FRAME.add_button('Start', start_handler, 100)
FRAME.add_button('Stop', stop_handler, 100)
FRAME.add_button('Reset', reset_handler, 100)

# start frame
FRAME.start()

# Please remember to review the grading rubric