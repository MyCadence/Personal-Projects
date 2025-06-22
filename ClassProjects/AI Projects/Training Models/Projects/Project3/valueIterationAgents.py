# valueIterationAgents.py
# -----------------------
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


# valueIterationAgents.py
# -----------------------
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


import mdp, util

from learningAgents import ValueEstimationAgent
import collections

class ValueIterationAgent(ValueEstimationAgent):
    """
        * Please read learningAgents.py before reading this.*

        A ValueIterationAgent takes a Markov decision process
        (see mdp.py) on initialization and runs value iteration
        for a given number of iterations using the supplied
        discount factor.
    """
    def __init__(self, mdp, discount = 0.9, iterations = 100):
        """
          Your value iteration agent should take an mdp on
          construction, run the indicated number of iterations
          and then act according to the resulting policy.

          Some useful mdp methods you will use:
              mdp.getStates()
              mdp.getPossibleActions(state)
              mdp.getTransitionStatesAndProbs(state, action)
              mdp.getReward(state, action, nextState)
              mdp.isTerminal(state)
        """
        self.mdp = mdp
        self.discount = discount
        self.iterations = iterations
        self.values = util.Counter() # A Counter is a dict with default 0
        self.runValueIteration()

    def runValueIteration(self):
        """
        Perform value iteration over the MDP.
        Terminal states have no reward, so their value is set to 0.
        Updates the values dictionary, where values[state] represents the value of that state.
        """
        # Perform k iterations of value iteration
        for _ in range(self.iterations):
            # Create a copy of the current values to store updated values during this iteration
            update_values_dict = self.values.copy()

            # Iterate through each state in the MDP
            for state in self.mdp.getStates():
                # If the state is terminal, its value is set to 0
                if self.mdp.isTerminal(state):
                    update_values_dict[state] = 0
                else:
                    # If it's not a terminal state, calculate the maximum Q-value across all actions
                    legal_actions = self.mdp.getPossibleActions(state)
                    Q_values = [self.getQValue(state, action) for action in legal_actions]

                    # Update the value for the current state to the maximum Q-value
                    update_values_dict[state] = max(Q_values) if Q_values else 0

            # After all states have been updated, set the current values to the updated values
            self.values = update_values_dict


    def getValue(self, state):
        """
          Return the value of the state (computed in __init__).
        """
        return self.values[state]


    def computeQValueFromValues(self, state, action):
        """
        Compute the Q-value of action in state from the
        value function stored in self.values.
        """
        # Initialize the Q-value to 0
        transition_states_probs = self.mdp.getTransitionStatesAndProbs(state, action)  # List of (nextState, probability)
        q_value = 0

        for next_state, probability in transition_states_probs:
            reward = self.mdp.getReward(state, action, next_state)
            future_value = self.discount * self.values[next_state]
            q_value += probability * (reward + future_value)

        return q_value

    def computeActionFromValues(self, state):
        """
        The policy is the best action in the given state
        according to the values currently stored in self.values.
        You may break ties any way you see fit. Note that if
        there are no legal actions, which is the case at the
        terminal state, you should return None.
        """

        possible_actions = self.mdp.getPossibleActions(state)
        if not possible_actions:
            return None

        best_action = max(possible_actions, key=lambda action: self.getQValue(state, action))
        return best_action

    def getPolicy(self, state):
        return self.computeActionFromValues(state)

    def getAction(self, state):
        "Returns the policy at the state (no exploration)."
        return self.computeActionFromValues(state)

    def getQValue(self, state, action):
        return self.computeQValueFromValues(state, action)

class AsynchronousValueIterationAgent(ValueIterationAgent):
    """
        * Please read learningAgents.py before reading this.*

        An AsynchronousValueIterationAgent takes a Markov decision process
        (see mdp.py) on initialization and runs cyclic value iteration
        for a given number of iterations using the supplied
        discount factor.
    """
    def __init__(self, mdp, discount = 0.9, iterations = 1000):
        """
          Your cyclic value iteration agent should take an mdp on
          construction, run the indicated number of iterations,
          and then act according to the resulting policy. Each iteration
          updates the value of only one state, which cycles through
          the states list. If the chosen state is terminal, nothing
          happens in that iteration.

          Some useful mdp methods you will use:
              mdp.getStates()
              mdp.getPossibleActions(state)
              mdp.getTransitionStatesAndProbs(state, action)
              mdp.getReward(state)
              mdp.isTerminal(state)
        """
        ValueIterationAgent.__init__(self, mdp, discount, iterations)

    def runValueIteration(self):
        states = self.mdp.getStates()

        # Initialize the value function for all states to 0
        self.values = {state: 0 for state in states}

        num_states = len(states)

        # Iterate for the specified number of iterations
        for i in range(self.iterations):
            state_index = i % num_states  # Select the state in a round-robin manner
            state = states[state_index]

            if not self.mdp.isTerminal(state):
                action = self.getAction(state)  # Get the best action for the current state
                qval = self.getQValue(state, action)  # Calculate the Q-value for the action
                self.values[state] = qval  # Update the value of the state

class PrioritizedSweepingValueIterationAgent(AsynchronousValueIterationAgent):
    """
        * Please read learningAgents.py before reading this.*

        A PrioritizedSweepingValueIterationAgent takes a Markov decision process
        (see mdp.py) on initialization and runs prioritized sweeping value iteration
        for a given number of iterations using the supplied parameters.
    """
    def __init__(self, mdp, discount = 0.9, iterations = 100, theta = 1e-5):
        """
          Your prioritized sweeping value iteration agent should take an mdp on
          construction, run the indicated number of iterations,
          and then act according to the resulting policy.
        """
        self.theta = theta
        ValueIterationAgent.__init__(self, mdp, discount, iterations)

    def runValueIteration(self):
        states = self.mdp.getStates()
        fringe = util.PriorityQueue()
        predecessors = {}

        # Initialize value function for all states and compute predecessors
        for s in states:
            self.values[s] = 0  # Initial value for each state
            predecessors[s] = self.get_predecessors(s)  # Get predecessors for state s

        # Push non-terminal states into the priority queue based on their value difference (diff)
        for s in states:
            if not self.mdp.isTerminal(s):
                current_value_of_state = self.values[s]
                diff = abs(current_value_of_state - self.max_Qvalue(s))
                fringe.push(s, -diff)  # Push with negative diff for max-priority behavior

        # Perform value iteration with prioritized sweeping
        for _ in range(self.iterations):
            if fringe.isEmpty():
                return  # If the fringe is empty, stop the iteration

            s = fringe.pop()  # Pop state with the highest priority (largest diff)
            self.values[s] = self.max_Qvalue(s)  # Update the state's value

            # Update predecessors of s, if their value difference is greater than theta, push to the queue
            for p in predecessors[s]:
                diff = abs(self.values[p] - self.max_Qvalue(p))
                if diff > self.theta:
                    fringe.update(p, -diff)  # Use negative diff to maintain priority for larger errors

    def get_predecessors(self, state):
        predecessor_set = set()
        states = self.mdp.getStates()  # Get all states in the MDP
        movements = ['north', 'south', 'east', 'west']  # Possible actions

        if not self.mdp.isTerminal(state):  # Only consider non-terminal states
            for p in states:
                if self.mdp.isTerminal(p):  # Skip terminal states
                    continue

                legal_actions = self.mdp.getPossibleActions(p)  # Actions available from state p

                # Check for each possible movement if it leads to the state `state`
                for move in movements:
                    if move in legal_actions:
                        # Get the possible transitions for the action from state p
                        transitions = self.mdp.getTransitionStatesAndProbs(p, move)

                        for s_prime, T in transitions:
                            # If this transition leads to the state `state` and has non-zero probability, it's a predecessor
                            if s_prime == state and T > 0:
                                predecessor_set.add(p)

        return predecessor_set
    
    def max_Qvalue(self, state):
    # Get the Q-value for each possible action and return the maximum
        return max(self.getQValue(state, a) for a in self.mdp.getPossibleActions(state))

