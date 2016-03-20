"""
Cookie Clicker Simulator
"""

import simpleplot
import math

# Used to increase the timeout, if necessary
import codeskulptor
codeskulptor.set_timeout(20)

import poc_clicker_provided as provided

# Constants
SIM_TIME = 10000000000.0

class ClickerState:
    """
    Simple class to keep track of the game state.
    """
    
    def __init__(self):
        self._total_cookies = 0
        self._cur_cookies = 0
        self._cur_time = 0
        self._cur_cps = 1.0
        self._history = [(0.0, None, 0.0, 0.0)]
        
    def __str__(self):
        """
        Return human readable state
        """
        return "Time: "+str(self._cur_time)+" Current Cookies: "+str(self._cur_cookies)+" CPS: " + str(self._cur_cps)+ " Total Cookies: "+ str(self._total_cookies)+" History (length: " + str(len(self._history)) + "):" + str(self._history)

    def get_cookies(self):
        """
        Return current number of cookies 
        (not total number of cookies)
        
        Should return a float
        """
        return float(self._cur_cookies)
    
    def get_cps(self):
        """
        Get current CPS

        Should return a float
        """
        return float(self._cur_cps)
    
    def get_time(self):
        """
        Get current time

        Should return a float
        """
        return float(self._cur_time)
    
    def get_history(self):
        """
        Return history list

        History list should be a list of tuples of the form:
        (time, item, cost of item, total cookies)

        For example: [(0.0, None, 0.0, 0.0)]

        Should return a copy of any internal data structures,
        so that they will not be modified outside of the class.
        """
        return list(self._history)

    def time_until(self, cookies):
        """
        Return time until you have the given number of cookies
        (could be 0.0 if you already have enough cookies)

        Should return a float with no fractional part
        """
        if self._cur_cookies >= cookies:
            time_to_wait = 0.0
        else:
            time_to_wait = math.ceil(float(cookies - self._cur_cookies)/self._cur_cps)
        return time_to_wait
    
    def wait(self, time):
        """
        Wait for given amount of time and update state

        Should do nothing if time <= 0.0
        """
        if time > 0.0:
            self._cur_time += time
            self._cur_cookies += self._cur_cps * time
            self._total_cookies += self._cur_cps * time
    
    def buy_item(self, item_name, cost, additional_cps):
        """
        Buy an item and update state

        Should do nothing if you cannot afford the item
        """
        if self._cur_cookies >= cost:
            self._cur_cookies -= cost
            self._cur_cps += additional_cps
            self._history.append((self._cur_time, item_name, cost, self._total_cookies))
   
    
def simulate_clicker(build_info, duration, strategy):
    """
    Function to run a Cookie Clicker game for the given
    duration with the given strategy.  Returns a ClickerState
    object corresponding to the final state of the game.
    """

    # Replace with your code
    bi_clone = build_info.clone() 
    clicker_state = ClickerState()
    while clicker_state.get_time() <= duration:
        time_left = duration - clicker_state.get_time()
        item = strategy(clicker_state.get_cookies(), clicker_state.get_cps(), clicker_state.get_history(), time_left, bi_clone)
        if item == None:
            clicker_state.wait(time_left)
            break
        else:
            cost = bi_clone.get_cost(item)
            wait = clicker_state.time_until(cost)
            if wait > time_left:
                clicker_state.wait(time_left)
                break
            else:
                clicker_state.wait(wait)
                clicker_state.buy_item(item, cost, bi_clone.get_cps(item))
                bi_clone.update_item(item)
    return clicker_state


def strategy_cursor_broken(cookies, cps, history, time_left, build_info):
    """
    Always pick Cursor!

    Note that this simplistic (and broken) strategy does not properly
    check whether it can actually buy a Cursor in the time left.  Your
    simulate_clicker function must be able to deal with such broken
    strategies.  Further, your strategy functions must correctly check
    if you can buy the item in the time left and return None if you
    can't.
    """
    return "Cursor"

def strategy_none(cookies, cps, history, time_left, build_info):
    """
    Always return None

    This is a pointless strategy that will never buy anything, but
    that you can use to help debug your simulate_clicker function.
    """
    return None

def strategy_cheap(cookies, cps, history, time_left, build_info):
    """
    Always buy the cheapest item you can afford in the time left.
    """
    bi_clone = build_info.clone()
    list_of_strategies = bi_clone.build_items()
    cheapest = float("inf")
    name = None
    prospecitve_cookies = cookies + cps * time_left
    for item in list_of_strategies:
        if bi_clone.get_cost(item) <= cheapest and prospecitve_cookies >= bi_clone.get_cost(item):
            cheapest = bi_clone.get_cost(item)
            name = item
    return name

def strategy_expensive(cookies, cps, history, time_left, build_info):
    """
    Always buy the most expensive item you can afford in the time left.
    """
    bi_clone = build_info.clone()
    list_of_strategies = bi_clone.build_items()
    expensive = float("-inf")
    name = None
    prospecitve_cookies = cookies + cps * time_left
    for item in list_of_strategies:
        if bi_clone.get_cost(item) >= expensive and prospecitve_cookies >= bi_clone.get_cost(item):
            expensive = bi_clone.get_cost(item)
            name = item
    return name

def strategy_best(cookies, cps, history, time_left, build_info):
    """
    The best strategy that you are able to implement.
    """
    bi_clone = build_info.clone()
    list_of_strategies = bi_clone.build_items()
    name = None
    prospecitve_cookies = cookies + cps * time_left
    lowest_cost_per_cookies = float('inf')
    most_efficient_item = ' '
    for item in list_of_strategies:
        item_cps = bi_clone.get_cps(item)
        item_cost = bi_clone.get_cost(item)
        item_per_cookie_cost = float(item_cost)/item_cps
        if item_per_cookie_cost < lowest_cost_per_cookies: 
            lowest_cost_per_cookies = item_per_cookie_cost
            most_efficient_item = item
    if bi_clone.get_cost(most_efficient_item) <= prospecitve_cookies:
        name = most_efficient_item
    return name
        
def run_strategy(strategy_name, time, strategy):
    """
    Run a simulation for the given time with one strategy.
    """
    state = simulate_clicker(provided.BuildInfo(), time, strategy)
    print strategy_name, ":", state

    # Plot total cookies over time

    # Uncomment out the lines below to see a plot of total cookies vs. time
    # Be sure to allow popups, if you do want to see it

    history = state.get_history()
    history = [(item[0], item[3]) for item in history]
    simpleplot.plot_lines(strategy_name, 1000, 400, 'Time', 'Total Cookies', [history], True)

def run():
    """
    Run the simulator.
    """    
    run_strategy("strategy_best", SIM_TIME, strategy_best)

    # Add calls to run_strategy to run additional strategies
    # run_strategy("Cheap", SIM_TIME, strategy_cheap)
    # run_strategy("Expensive", SIM_TIME, strategy_expensive)
    # run_strategy("Best", SIM_TIME, strategy_best)
    
run()

    