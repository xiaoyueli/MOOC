# program template for Spaceship
import simplegui
import math
import random

# globals for user interface
WIDTH = 800
HEIGHT = 600
score = 0
lives = 3
time = 0
a_missile = None
a_rock = None
started = False

class ImageInfo:
    def __init__(self, center, size, radius = 0, lifespan = None, animated = False):
        self.center = center
        self.size = size
        self.radius = radius
        if lifespan:
            self.lifespan = lifespan
        else:
            self.lifespan = float('inf')
        self.animated = animated

    def get_center(self):
        return self.center

    def get_size(self):
        return self.size

    def get_radius(self):
        return self.radius

    def get_lifespan(self):
        return self.lifespan

    def get_animated(self):
        return self.animated

    
# art assets created by Kim Lathrop, may be freely re-used in non-commercial projects, please credit Kim
    
# debris images - debris1_brown.png, debris2_brown.png, debris3_brown.png, debris4_brown.png
#                 debris1_blue.png, debris2_blue.png, debris3_blue.png, debris4_blue.png, debris_blend.png
debris_info = ImageInfo([320, 240], [640, 480])
debris_image = simplegui.load_image("http://commondatastorage.googleapis.com/codeskulptor-assets/lathrop/debris2_blue.png")

# nebula images - nebula_brown.png, nebula_blue.png
nebula_info = ImageInfo([400, 300], [800, 600])
nebula_image = simplegui.load_image("http://commondatastorage.googleapis.com/codeskulptor-assets/lathrop/nebula_blue.f2014.png")

# splash image
splash_info = ImageInfo([200, 150], [400, 300])
splash_image = simplegui.load_image("http://commondatastorage.googleapis.com/codeskulptor-assets/lathrop/splash.png")

# ship image
ship_info = ImageInfo([45, 45], [90, 90], 35)
ship_image = simplegui.load_image("http://commondatastorage.googleapis.com/codeskulptor-assets/lathrop/double_ship.png")

# missile image - shot1.png, shot2.png, shot3.png
missile_info = ImageInfo([5,5], [10, 10], 3, 50)
missile_image = simplegui.load_image("http://commondatastorage.googleapis.com/codeskulptor-assets/lathrop/shot2.png")

# asteroid images - asteroid_blue.png, asteroid_brown.png, asteroid_blend.png
asteroid_info = ImageInfo([45, 45], [90, 90], 40)
asteroid_image = simplegui.load_image("http://commondatastorage.googleapis.com/codeskulptor-assets/lathrop/asteroid_blue.png")

# animated explosion - explosion_orange.png, explosion_blue.png, explosion_blue2.png, explosion_alpha.png
explosion_info = ImageInfo([64, 64], [128, 128], 17, 24, True)
explosion_image = simplegui.load_image("http://commondatastorage.googleapis.com/codeskulptor-assets/lathrop/explosion_alpha.png")

# sound assets purchased from sounddogs.com, please do not redistribute
soundtrack = simplegui.load_sound("http://commondatastorage.googleapis.com/codeskulptor-assets/sounddogs/soundtrack.mp3")
missile_sound = simplegui.load_sound("http://commondatastorage.googleapis.com/codeskulptor-assets/sounddogs/missile.mp3")
missile_sound.set_volume(.5)
ship_thrust_sound = simplegui.load_sound("http://commondatastorage.googleapis.com/codeskulptor-assets/sounddogs/thrust.mp3")
explosion_sound = simplegui.load_sound("http://commondatastorage.googleapis.com/codeskulptor-assets/sounddogs/explosion.mp3")

# alternative upbeat soundtrack by composer and former IIPP student Emiel Stopler
# please do not redistribute without permission from Emiel at http://www.filmcomposer.nl
#soundtrack = simplegui.load_sound("https://storage.googleapis.com/codeskulptor-assets/ricerocks_theme.mp3")

# helper functions to handle transformations
def angle_to_vector(ang):
    """
    convert angle to vector
    """
    return [math.cos(ang), math.sin(ang)]

def dist(p,q):
    """
    compute the distance of two point
    """
    return math.sqrt((p[0] - q[0]) ** 2+(p[1] - q[1]) ** 2)


# Ship class
class Ship:
    def __init__(self, pos, vel, angle, image, info):
        """
        initialize the arguments of instance
        """
        self.pos = [pos[0], pos[1]]
        self.vel = [vel[0], vel[1]]
        self.thrust = False
        self.angle = angle
        self.angle_vel = 0
        self.image = image
        self.image_center = info.get_center()
        self.image_size = info.get_size()
        self.radius = info.get_radius()
        
    def draw(self,canvas):
        """
        draw ship
        """
        image_y = self.image_center[1]
        if self.thrust:
            image_x = self.image_center[0] + self.image_size[0]
        else:
            image_x = self.image_center[0]
        canvas.draw_image(self.image, [image_x, image_y], self.image_size, self.pos, self.image_size, self.angle)

   
    def update(self):
        """
        update status
        """
        # update angle
        self.angle += self.angle_vel
        
        # update position
        self.pos[0] += self.vel[0]
        self.pos[0] %= WIDTH
        self.pos[1] += self.vel[1]
        self.pos[1] %= HEIGHT
        
        # update velocity
        if self.thrust:
            vector = angle_to_vector(self.angle)
            self.vel[0] += vector[0] * 0.1
            self.vel[1] += vector[1] * 0.1
     
        self.vel[0] *= 0.99
        self.vel[1] *= 0.99

    def incre_angle_vel(self):
        """
        increase angle velocity
        """
        self.angle_vel += 0.05
        
    def decre_angle_vel(self):
        """
        decrease angle velocity
        """
        self.angle_vel -= 0.05
    
    def reset_angle_vel(self):
        """
        reset angle velocity
        """
        self.angle_vel = 0

    def is_thrust(self, bol):
        """
        if the ship is thrust, play ship_thrust_sound
        """
        self.thrust = bol
        if self.thrust:
            ship_thrust_sound.play()
        else:
            ship_thrust_sound.rewind()

    def shoot(self, sound):
        """
        create a missile and add to missile_group
        """
        global a_missile
        pos = [self.pos[0] + self.image_size[0]/2 * angle_to_vector(self.angle)[0], self.pos[1] + self.image_size[1]/2 * angle_to_vector(self.angle)[1]]
        vel = [self.vel[0] + angle_to_vector(self.angle)[0] * 5, self.vel[1] + angle_to_vector(self.angle)[1] * 5]
        a_missile = Sprite(pos, vel, 0, 0, missile_image, missile_info, sound)
        missile_group.add(a_missile)

    def get_radius(self):
        return self.radius

    def get_position(self):
        return self.pos

    def get_vel(self):
        return self.vel

# Sprite class
class Sprite:
    def __init__(self, pos, vel, ang, ang_vel, image, info, sound = None,):
        self.pos = [pos[0], pos[1]]
        self.vel = [vel[0], vel[1]]
        self.angle = ang
        self.angle_vel = ang_vel
        self.image = image
        self.image_center = info.get_center()
        self.image_size = info.get_size()
        self.radius = info.get_radius()
        self.lifespan = info.get_lifespan()
        self.animated = info.get_animated()
        self.age = 0
        if sound:
            sound.rewind()
            sound.play()
   
    def draw(self, canvas):
        center = list(self.image_center)
        if self.animated:
            center[0] += self.age * self.image_size[0]
        canvas.draw_image(self.image, center, self.image_size, self.pos, self.image_size, self.angle)
    
    def update(self):
        self.angle += self.angle_vel
        self.pos[0] += self.vel[0]
        self.pos[0] %= WIDTH
        self.pos[1] += self.vel[1]
        self.pos[1] %= HEIGHT
        self.age += 1
        if self.age >=self.lifespan:
            return True
        return False

    def collide(self, other_object):
        distance = dist(self.pos, other_object.get_position())
        if distance <= (self.radius + other_object.get_radius()):
            return True
        return False

    def get_position(self):
        return self.pos
           
    def get_radius(self):
        return self.radius
    
    def get_vel(self):
        return self.vel

def draw(canvas):
    global time, lives, score, started
    global rock_group
    
    
    # animiate background
    time += 1
    wtime = (time / 4) % WIDTH
    center = debris_info.get_center()
    size = debris_info.get_size()
    canvas.draw_image(nebula_image, nebula_info.get_center(), nebula_info.get_size(), [WIDTH / 2, HEIGHT / 2], [WIDTH, HEIGHT])
    canvas.draw_image(debris_image, center, size, (wtime - WIDTH / 2, HEIGHT / 2), (WIDTH, HEIGHT))
    canvas.draw_image(debris_image, center, size, (wtime + WIDTH / 2, HEIGHT / 2), (WIDTH, HEIGHT))

    # draw ship
    my_ship.draw(canvas)

    # update ship
    my_ship.update()
    
    # update sprites
    process_sprite_group(rock_group, canvas)
    process_sprite_group(missile_group, canvas)
    process_sprite_group(explosion_group, canvas)

    if started:

        # update lives
        if group_collide(rock_group, my_ship):
            lives -= 1

        #uodate score
        score += group_group_collide(rock_group, missile_group) 



        # reset game
        if lives == 0:
            rock_group = set([])
            started = False
        
    else:
        spl_center = splash_info.get_center()
        spl_size = splash_info.get_size()
        canvas.draw_image(splash_image, spl_center, spl_size, (WIDTH/2, HEIGHT/2), spl_size)
    
    # draw score and lives
    canvas.draw_text("Life: "+str(lives), [WIDTH/15, HEIGHT/15], 24, 'white')
    canvas.draw_text("Score: "+str(score), [WIDTH - 3 * WIDTH/15, HEIGHT/15], 24, 'white')        

# timer handler that spawns a rock    
def rock_spawner():
    global rock_group, started
    if len(rock_group)<12 and started:   
        vel = [(random.random() * 0.6 - 0.3) * (1 + score * 0.1),
               (random.random() * 0.6 - 0.3) * (1 + score * 0.1)]
        pos = [random.randrange(0, WIDTH), random.randrange(0, HEIGHT)]
        ang = (random.random() * 0.2 - 0.1) * (1 + score * 0.02)
        a_rock = Sprite(pos, vel, 0, ang, asteroid_image, asteroid_info)
        if dist(pos, my_ship.get_position()) > (a_rock.get_radius() + my_ship.get_radius()):
            rock_group.add(a_rock)

def process_sprite_group (sprite_group, canvas):
    for sprite in set(sprite_group):
        if sprite.update():
            sprite_group.remove(sprite)
        sprite.draw(canvas)

def group_collide(group, other_object):
    global explosion_group
    is_collide = False
    for obj in set(group):
        if obj.collide(other_object):
            group.remove(obj)
            is_collide = True
            an_explosion = Sprite(other_object.get_position(), other_object.get_vel(),
                                    0, 0, explosion_image, explosion_info, explosion_sound)
            explosion_group.add(an_explosion)
    return is_collide

def group_group_collide(group_target, group_weapon):
    global explosion_group
    destroy_num = 0
    for obj in set(group_target):
        for weapon in set(group_weapon):
            if obj.collide(weapon):
                group_target.discard(obj)
                group_weapon.discard(weapon)
                destroy_num += 1
                an_explosion = Sprite(obj.get_position(), obj.get_vel(),
                                0, 0, explosion_image, explosion_info, explosion_sound)
                explosion_group.add(an_explosion)
                break
    return destroy_num

def keydown(key):
    if key == simplegui.KEY_MAP['space']:
        my_ship.shoot(missile_sound)
    if key == simplegui.KEY_MAP['left']:
        my_ship.decre_angle_vel()
    if key == simplegui.KEY_MAP['right']:
        my_ship.incre_angle_vel()
    if key == simplegui.KEY_MAP['up']:
        my_ship.is_thrust(True)


def keyup(key):
    if key == simplegui.KEY_MAP['left']:
        my_ship.reset_angle_vel()
    elif key == simplegui.KEY_MAP['right']:
        my_ship.reset_angle_vel()
    elif key == simplegui.KEY_MAP['up']:
        my_ship.is_thrust(False)

def click(pos):
    global started, lives, score
    center = [WIDTH / 2, HEIGHT / 2]
    size = splash_info.get_size()
    inwidth = (center[0] - size[0] / 2) <  pos[0] < (center[0] + size[0]/2)
    inheight = (center[1] - size[1] / 2) < pos[1] < (center[1] + size[1] / 2)
    if not started and inwidth and inheight:
        started = True
        soundtrack.rewind()
        soundtrack.play()
        lives = 3
        score = 0
    
# initialize frame
frame = simplegui.create_frame("Asteroids", WIDTH, HEIGHT)

# initialize ship and two sprites
my_ship = Ship([WIDTH / 2, HEIGHT / 2], [0, 0], 0, ship_image, ship_info)
rock_group = set([])
missile_group = set([])
explosion_group = set([])

# register handlers
frame.set_draw_handler(draw)
frame.set_keydown_handler(keydown)
frame.set_keyup_handler(keyup)
frame.set_mouseclick_handler(click)

timer = simplegui.create_timer(1000.0, rock_spawner)


# get things rolling
timer.start()
frame.start()