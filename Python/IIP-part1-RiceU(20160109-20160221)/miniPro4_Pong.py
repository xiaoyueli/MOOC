# Implementation of classic arcade game Pong

import simplegui
import random

# initialize globals - pos and vel encode vertical info for paddles
WIDTH = 600
HEIGHT = 400       
BALL_RADIUS = 20
PAD_WIDTH = 8
PAD_HEIGHT = 80
HALF_PAD_WIDTH = PAD_WIDTH / 2
HALF_PAD_HEIGHT = PAD_HEIGHT / 2
LEFT = False
RIGHT = True

# initialize ball_pos and ball_vel for new bal in middle of table
# if direction is RIGHT, the ball's velocity is upper right, else upper left
def spawn_ball(direction):
    """
    Initialize ball_pos and ball_vel of random numbers
    """
    global ball_pos, ball_vel # these are vectors stored as lists
    ball_pos = [WIDTH / 2, HEIGHT / 2]
    vel_x = random.randrange(120, 240) / 60
    vel_y = random.randrange(60, 180) / 60
    if direction == RIGHT:
        ball_vel = [vel_x, -vel_y]
    else:
        ball_vel = [-vel_x, -vel_y]

# define event handlers
def new_game():
    """
    Initialize new game
    """
    global paddle1_pos, paddle2_pos, paddle1_vel, paddle2_vel  # these are numbers
    global score1, score2  # these are ints
    paddle1_pos = HEIGHT / 2
    paddle2_pos = HEIGHT / 2
    paddle1_vel = 0
    paddle2_vel = 0
    score1 = 0
    score2 = 0
    spawn_ball(True)

def draw(canvas):
    """
    Draw ball and paddles with Updating data immediately
    """
    global score1, score2, paddle1_pos, paddle2_pos, ball_pos, ball_vel 
        
    # draw mid line and gutters
    canvas.draw_line([WIDTH / 2, 0],[WIDTH / 2, HEIGHT], 1, "White")
    canvas.draw_line([PAD_WIDTH, 0],[PAD_WIDTH, HEIGHT], 1, "White")
    canvas.draw_line([WIDTH - PAD_WIDTH, 0],[WIDTH - PAD_WIDTH, HEIGHT], 1, "White")
        
    # update ball
    ball_pos[0] += ball_vel[0]
    ball_pos[1] += ball_vel[1]
            
    # draw ball
    canvas.draw_circle(ball_pos, BALL_RADIUS, 1, 'White', 'White')
    
    # update paddle's vertical position, keep paddle on the screen
    if paddle1_pos - HALF_PAD_HEIGHT < 0:
        paddle1_pos  = HALF_PAD_HEIGHT
    elif paddle1_pos + HALF_PAD_HEIGHT > HEIGHT:
        paddle1_pos = HEIGHT - HALF_PAD_HEIGHT
    else:
        paddle1_pos += paddle1_vel
    if paddle2_pos - HALF_PAD_HEIGHT < 0:
        paddle2_pos  = HALF_PAD_HEIGHT
    elif paddle2_pos + HALF_PAD_HEIGHT > HEIGHT:
        paddle2_pos = HEIGHT - HALF_PAD_HEIGHT
    else:
        paddle2_pos += paddle2_vel
 
    # draw paddles
    canvas.draw_polygon([[1, paddle1_pos - HALF_PAD_HEIGHT], [PAD_WIDTH, paddle1_pos - HALF_PAD_HEIGHT], [PAD_WIDTH, paddle1_pos + HALF_PAD_HEIGHT], [1, paddle1_pos + HALF_PAD_HEIGHT]], 1, 'White', 'White')
    canvas.draw_polygon([[WIDTH - PAD_WIDTH, paddle2_pos - HALF_PAD_HEIGHT], [WIDTH - 1, paddle2_pos - HALF_PAD_HEIGHT], [WIDTH - 1, paddle2_pos + HALF_PAD_HEIGHT], [WIDTH - PAD_WIDTH, paddle2_pos + HALF_PAD_HEIGHT]], 1, 'White', 'White')
    
    # determine whether paddle and ball collide    
    ball_up = ball_pos[1] - BALL_RADIUS
    ball_down = ball_pos[1] + BALL_RADIUS
    ball_right = ball_pos[0] + BALL_RADIUS
    ball_left = ball_pos[0] - BALL_RADIUS

    paddle1_up = paddle1_pos - HALF_PAD_HEIGHT
    paddle1_down = paddle1_pos + HALF_PAD_HEIGHT
    paddle2_up = paddle2_pos - HALF_PAD_HEIGHT
    paddle2_down = paddle2_pos + HALF_PAD_HEIGHT

    # the ball bounces off of the top and bottom walls
    if ball_up < 0 or ball_down > HEIGHT:
        ball_vel[1] = - ball_vel[1]

    if ball_left <= PAD_WIDTH:
        if ball_pos[1] >= paddle1_up and ball_pos[1] <= paddle1_down:
            ball_vel[0] = - ball_vel[0] * 1.1   # the ball bounces off if it touches the paddles
        else:
            spawn_ball(RIGHT)  # the ball respawns 
            score2 += 1
    if ball_right >= WIDTH - PAD_WIDTH:
        if ball_pos[1] >= paddle2_up and ball_pos[1] <= paddle2_down:
            ball_vel[0] = - ball_vel[0] * 1.1 
        else:
            spawn_ball(LEFT)
            score1 += 1
    
    # draw scores
    canvas.draw_text(str(score1), (WIDTH/2/2, HEIGHT/2/2), 36, 'White')
    canvas.draw_text(str(score2), (WIDTH - WIDTH/2/2, HEIGHT/2/2), 36, 'White')
        
def keydown(key):
    """
    Update the velocity of paddles according to the corresponding key
    """
    global paddle1_vel, paddle2_vel
    acc = 5
    if key == simplegui.KEY_MAP['w']:
        paddle1_vel -= acc
    elif key == simplegui.KEY_MAP['s']:
        paddle1_vel += acc
    if key == simplegui.KEY_MAP['down']:
        paddle2_vel += acc
    elif key == simplegui.KEY_MAP['up']:
        paddle2_vel -= acc
   
def keyup(key):
    """
    the paddles are motionless when the corresponding key is not pressed
    """
    global paddle1_vel, paddle2_vel
    if key == simplegui.KEY_MAP['w'] or key == simplegui.KEY_MAP['s']:
        paddle1_vel = 0
    if key == simplegui.KEY_MAP['up'] or key == simplegui.KEY_MAP['down']:
        paddle2_vel = 0
        
def restart_handler():
    """
    restart the game
    """
    new_game()

# create frame
frame = simplegui.create_frame("Pong", WIDTH, HEIGHT)
frame.set_draw_handler(draw)
frame.set_keydown_handler(keydown)
frame.set_keyup_handler(keyup)
frame.add_button('restart', restart_handler, 100)

# start frame
new_game()
frame.start()