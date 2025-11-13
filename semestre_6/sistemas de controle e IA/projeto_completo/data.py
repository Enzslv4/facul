positions = {
    "line1": ['x','x','x','x'],
    "line2": ['x','x','x','x'],
    "line3": ['x','x','x','x'],
    "line4": [' ',' ',' ',' '],
    "line5": [' ',' ',' ',' '],
    "line6": ['o','o','o','o'],
    "line7": ['o','o','o','o'],
    "line8": ['o','o','o','o'],
}

board = f"""
    A     B     C     D     E     F     G     H
8|     |  {positions['line1'][0]}  |     |  {positions['line1'][1]}  |     |  {positions['line1'][2]}  |     |  {positions['line1'][3]}  
 |_______________________________________________
7|  {positions['line2'][0]}  |     |  {positions['line2'][1]}  |     |  {positions['line2'][2]}  |     |  {positions['line2'][3]}  |     
 |_______________________________________________
6|     |  {positions['line3'][0]}  |     |  {positions['line3'][1]}  |     |  {positions['line3'][2]}  |     |  {positions['line3'][3]}  
 |_______________________________________________
5|  {positions['line4'][0]}  |     |  {positions['line4'][1]}  |     |  {positions['line4'][2]}  |     |  {positions['line4'][3]}  |    
 |_______________________________________________
4|     |  {positions['line5'][0]}  |     |  {positions['line5'][1]}  |     |  {positions['line5'][2]}  |     |  {positions['line5'][3]}  
 |_______________________________________________
3|  {positions['line6'][0]}  |     |  {positions['line6'][1]}  |     |  {positions['line6'][2]}  |     |  {positions['line6'][3]}  |     
 |_______________________________________________
2|     |  {positions['line7'][0]}  |     |  {positions['line7'][1]}  |     |  {positions['line7'][2]}  |     |  {positions['line7'][3]}  
 |_______________________________________________
1|  {positions['line8'][0]}  |     |  {positions['line8'][1]}  |     |  {positions['line8'][2]}  |     |  {positions['line8'][3]}  |     
"""

