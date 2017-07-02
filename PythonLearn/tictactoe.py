# Simple program that takes a two dimensional array as an input
# Notation: 0 is empty, 1 is player1, 2 is player2
# 

import sys
#import Enum, auto


#Enum is available only from 3.6.2
#class BoardStatus(Enum):
#    IP = auto()
#    WINNER1 = auto()
#    WINNER2 = auto()

WIP = 0
WINNER1 = 1
WINNER2 = 2
DRAW = 3
     
def validate(columns):
    for c in columns:
        if int(c) != 0 and int(c) != 1 and int(c) != 2:
            print('Invalid Column value {:d}'.format(int(c)))
            return False  
    return True

def readBoard(numDims):
    board = [[-1 for x in range(numDims)] for y in range(numDims)]
    
    with open("tt.inp") as f:
        content = f.readlines()

    row = 0
    
    for x in content:
        allCols = x.rstrip('\n').split(",")
        if len(allCols) != numDims:
            print('Row : {:d}, Column Size {:d} != {:d}'.format(row, len(allCols), numDims))
            return None
        status = validate(allCols) 
        if (status == False):
            return None
        
        board[row] [0] = allCols[0]
        board[row] [1] = allCols[1]
        board[row] [2] = allCols[2]

        row = row + 1
        if (row > numDims):
            print('Row Mismatch. Expected [{:d}] != Found [{:d}]'.format(row, numDims))
            return None
            
    return board    

#Flawed implementatioin. Just trying out reduce.
def checkInternal1(array, id, total):
    matches = list(filter(lambda x: int(x) == id, array))
    if (len(matches) == 0):
        return False
    sum = reduce((lambda x, y : int(x) + int(y)), matches)
    return (sum == total)

def checkInternal(array, id, numDims):
    matches = list(filter(lambda x: int(x) == id, array))
    return (len(matches) == numDims)
    
def checkWinner(id, board, numDims):
    #check rows
    for row in board:
        if (checkInternal(row, id, numDims)):
            return True
    
    #check columns
    for y in range(numDims):
       colElements = [row[y] for row in board]
       if (checkInternal(colElements, id, numDims)):
           return True
           
    #check forward diagonal
    fwdDiagElements = []
    for x in range(numDims):
        for y in range(numDims):
            if (x == y):
                fwdDiagElements.append(board[x][y])
    
    if (checkInternal(fwdDiagElements, id, numDims)):
        return True
    
    #check back diagonal
    backDiagonalElements = []
    for x in range(numDims):
        for y in range(numDims):
            if (int(x) + int(y) == numDims - 1):
                backDiagonalElements.append(board[y][x])
    if (checkInternal(backDiagonalElements, id, numDims)):
        return True
    
        
def checkForWinner(board, numDims):
    if (checkWinner(1, board, numDims)):
        return WINNER1
    elif (checkWinner(2, board, numDims)):
        return WINNER2
    else:
        for row in board:    
            zeroElements = list(filter(lambda x: int(x) == 0, row))
            if (len(zeroElements) > 0):
                return WIP
    return DRAW
    
def main(argv):
    numDims = 3
    board = readBoard(numDims)
    if (board != None):
        print (board)
    else:
        print ('Input Error')
        exit()
        
    status = checkForWinner(board, numDims)
    if (status == WIP):
        print ('In Progress')
    elif (status == WINNER1):
        print ('Winner 1')
    elif (status == WINNER2):
        print ("Winner 2")
    else:
        print ("Draw")
        
    
if __name__ == "__main__":
    main(sys.argv)

