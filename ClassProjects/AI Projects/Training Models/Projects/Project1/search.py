# search.py
# ---------
# Licensing Information:  You are free to use or extend these projects for
# educational purposes provided that (1) you do not distribute or publish
# solutions, (2) you retain this notice, and (3) you provide clear
# attribution to UC Berkeley, including a link to http://ai.berkeley.edu.
# 
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


"""
In search.py, you will implement generic search algorithms which are called by
Pacman agents (in searchAgents.py).
"""

import util

class SearchProblem:
    """
    This class outlines the structure of a search problem, but doesn't implement
    any of the methods (in object-oriented terminology: an abstract class).

    You do not need to change anything in this class, ever.
    """

    def getStartState(self):
        """
        Returns the start state for the search problem.
        """
        util.raiseNotDefined()

    def isGoalState(self, state):
        """
          state: Search state

        Returns True if and only if the state is a valid goal state.
        """
        util.raiseNotDefined()

    def getSuccessors(self, state):
        """
          state: Search state

        For a given state, this should return a list of triples, (successor,
        action, stepCost), where 'successor' is a successor to the current
        state, 'action' is the action required to get there, and 'stepCost' is
        the incremental cost of expanding to that successor.
        """
        util.raiseNotDefined()

    def getCostOfActions(self, actions):
        """
         actions: A list of actions to take

        This method returns the total cost of a particular sequence of actions.
        The sequence must be composed of legal moves.
        """
        util.raiseNotDefined()


def tinyMazeSearch(problem):
    """
    Returns a sequence of moves that solves tinyMaze.  For any other maze, the
    sequence of moves will be incorrect, so only use this for tinyMaze.
    """
    from game import Directions
    s = Directions.SOUTH
    w = Directions.WEST
    return  [s, s, w, s, w, w, s, w]

def depthFirstSearch(problem):
    """
    Search the deepest nodes in the search tree first.

    Your search algorithm needs to return a list of actions that reaches the
    goal. Make sure to implement a graph search algorithm.

    To get started, you might want to try some of these simple commands to
    understand the search problem that is being passed in:

    print("Start:", problem.getStartState())
    print("Is the start a goal?", problem.isGoalState(problem.getStartState()))
    print("Start's successors:", problem.getSuccessors(problem.getStartState()))
    """
    "*** YOUR CODE HERE ***"
    from util import Stack
    path = []                         # Final list of directions
    
    
    
    visited = []                      # List to track visited states
    pathToCurrent = Stack()           # Stack to maintain path from start to current state
    openStates = Stack()              # Potential states we can take
    
    openStates.push(problem.getStartState())
    # Initialize current state
    lastState = openStates.pop()

    # Continue until the goal state is found
    while not problem.isGoalState(lastState):
        # If the current state has not been visited, explore it
        if lastState not in visited:
            visited.append(lastState)  # Mark state as visited
            successors = problem.getSuccessors(lastState)
            
            # Iterate through successors to push child states and update paths
            for child, direction, cost in successors:
                openStates.push(child)
                tempPath = path + [direction]  # Update temporary path with new direction
                pathToCurrent.push(tempPath)
        
        # Pop the next state and path
        lastState = openStates.pop()
        path = pathToCurrent.pop()
        

        # Return the path to the goal state

    return path

    #util.raiseNotDefined()

def breadthFirstSearch(problem):
    from util import Queue
    path = []                         # Final list of directions
    
    
    
    visited = []                      # List to track visited states
    pathToCurrent = Queue()           # Stack to maintain path from start to current state
    openStates = Queue()              # Potential states we can take
    
    openStates.push(problem.getStartState())
    # Initialize current state
    lastState = openStates.pop()

    # Continue until the goal state is found
    while not problem.isGoalState(lastState):
        # If the current state has not been visited, explore it
        if lastState not in visited:
            visited.append(lastState)  # Mark state as visited
            successors = problem.getSuccessors(lastState)
            
            # Iterate through successors to push child states and update paths
            for child, direction, cost in successors:
                openStates.push(child)
                tempPath = path + [direction]  # Update temporary path with new direction
                pathToCurrent.push(tempPath)
        
        # Pop the next state and path
        lastState = openStates.pop()
        path = pathToCurrent.pop()
        

        # Return the path to the goal state
    # print(lastState) # test print functions
    # print(direction)
    return path
    #util.raiseNotDefined()

def uniformCostSearch(problem):
    """Search the node of least total cost first."""
    from util import Queue, PriorityQueue
    fringe = PriorityQueue()
    stateToPath = PriorityQueue()  
    visited = []
    finalPath = []
    intermediatePath = []
    
    # Push initial state
    initialState = problem.getStartState()
    fringe.push(initialState, 0)
    currentState = fringe.pop()
    
    while True:  # Using an infinite loop with break
        if problem.isGoalState(currentState):
            break
        
        if currentState in visited:
            currentState = fringe.pop()
            finalPath = stateToPath.pop()
            continue  # Skip the rest and jump to next iteration
        
        visited.append(currentState)
        successors = problem.getSuccessors(currentState)

        for child, direction, cost in successors:
            intermediatePath = finalPath + [direction]
            totalCost = problem.getCostOfActions(intermediatePath)
            
            if child in visited:
                continue  # Skip adding visited children
            
            # Push to fringe and path tracker
            fringe.push(child, totalCost)
            stateToPath.push(intermediatePath, totalCost)
        
        # Update state and path at the end of the loop
        currentState = fringe.pop()
        finalPath = stateToPath.pop()

    return finalPath
    #util.raiseNotDefined()

def nullHeuristic(state, problem=None):
    """
    A heuristic function estimates the cost from the current state to the nearest
    goal in the provided SearchProblem.  This heuristic is trivial.
    """
    return 0

def aStarSearch(problem, heuristic=nullHeuristic):
    """Search the node that has the lowest combined cost and heuristic first."""
    from util import Queue, PriorityQueue

    # Priority queue to manage states to expand
    stateToPath = PriorityQueue()
    fringe = PriorityQueue()
    
    # Push initial state to the fringe
    initialState = problem.getStartState()
    fringe.push(initialState, 0)
    
    # Initialize visited and path variables
    visited = []
    finalPath = []
    intermediatePath = []
    
    # Pop the first state
    currentState = fringe.pop()

    while True:
        if problem.isGoalState(currentState):
            break
        
        if currentState in visited:
            currentState = fringe.pop()
            finalPath = stateToPath.pop()
            continue  # Skip already visited states
        
        # Mark the state as visited
        visited.append(currentState)
        successors = problem.getSuccessors(currentState)

        # Iterate through successors
        for child, direction, cost in successors:
            intermediatePath = finalPath + [direction]
            totalCost = problem.getCostOfActions(intermediatePath) + heuristic(child, problem)

            if child in visited:
                continue  # Skip adding visited children
            
            # Push child state and cost to the fringe and stateToPath queue
            fringe.push(child, totalCost)
            stateToPath.push(intermediatePath, totalCost)

        # Update the current state and final path
        currentState = fringe.pop()
        finalPath = stateToPath.pop()

    return finalPath



# Abbreviations
bfs = breadthFirstSearch
dfs = depthFirstSearch
astar = aStarSearch
ucs = uniformCostSearch
