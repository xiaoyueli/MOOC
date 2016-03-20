"""
Merge function for 2048 game.
"""

def merge(line):
    """
    Function that merges a single row or column in 2048.
    """
    # replace with your code
    new_line = [0 for idx in range(len(line))]
    idx = 0
    curr_val = 0
    for val in line:
        if curr_val == val and val != 0:
            new_line[idx] = curr_val + val
            idx += 1
            curr_val = 0
        elif curr_val != val and val != 0:
            if curr_val == 0:
                curr_val = val
            else:
                new_line[idx] = curr_val
                idx += 1
                curr_val = val
                
    if curr_val != 0:
        new_line[idx] = curr_val
        
    return new_line


