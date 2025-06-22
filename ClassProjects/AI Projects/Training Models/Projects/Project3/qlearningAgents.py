# qlearningAgents.py
# ------------------
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


from game import *
from pacman import Directions
from learningAgents import ReinforcementAgent
from featureExtractors import *

import random,util,math

class QLearningAgent(ReinforcementAgent):
    """
      Q-Learning Agent

      Functions you should fill in:
        - computeValueFromQValues
        - computeActionFromQValues
        - getQValue
        - getAction
        - update

      Instance variables you have access to
        - self.epsilon (exploration prob)
        - self.alpha (learning rate)
        - self.discount (discount rate)

      Functions you should use
        - self.getLegalActions(state)
          which returns legal actions for a state
    """
    def __init__(self, **args):
        "You can initialize Q-values here..."
        ReinforcementAgent.__init__(self, **args)

        self.qValues = {}  # Initialize the dictionary to store Q-values

    def getQValue(self, state, action):
        """
          Returns Q(state,action)
          Should return 0.0 if we have never seen a state
          or the Q node value otherwise
        """
        "*** YOUR CODE HERE ***"
        return self.qValues.get((state, action), 0.0)


    def computeValueFromQValues(self, state):
        """
          Returns max_action Q(state,action)
          where the max is over legal actions.  Note that if
          there are no legal actions, which is the case at the
          terminal state, you should return a value of 0.0.
        """
        "*** YOUR CODE HERE ***"
        legalActions = self.getLegalActions(state)
        if not legalActions:
            return 0.0  # Return 0.0 if no legal actions (terminal state)
        
        # Return the maximum Q-value among all legal actions
        return max(self.getQValue(state, action) for action in legalActions)

    def computeActionFromQValues(self, state):
        legalActions = self.getLegalActions(state)
        if not legalActions:
            return None  # Return None if no legal actions (terminal state)

        # Find the action(s) with the maximum Q-value
        maxQValue = max(self.getQValue(state, action) for action in legalActions)

        # Get all actions with the maximum Q-value
        bestActions = [action for action in legalActions if self.getQValue(state, action) == maxQValue]

        # Randomly pick one of the best actions (to break ties)
        return random.choice(bestActions)
    
    def getAction(self, state):
        """
          Compute the action to take in the current state.  With
          probability self.epsilon, we should take a random action and
          take the best policy action otherwise.  Note that if there are
          no legal actions, which is the case at the terminal state, you
          should choose None as the action.

          HINT: You might want to use util.flipCoin(prob)
          HINT: To pick randomly from a list, use random.choice(list)
        """
        # Pick Action
        legalActions = self.getLegalActions(state)
        action = None
        "*** YOUR CODE HERE ***"
        if len(legalActions) == 0:
            return None  # If there are no legal actions, return None

        # Exploration: Choose a random action with probability epsilon
        if util.flipCoin(self.epsilon):
            return random.choice(legalActions)

        # Exploitation: Choose the best action based on Q-values
        action = self.computeActionFromQValues(state)
        return action

    def update(self, state, action, nextState, reward):
        currentQValue = self.getQValue(state, action)
        futureQValue = self.computeValueFromQValues(nextState)
        
        # Q-learning update rule
        updatedQValue = (1 - self.alpha) * currentQValue + self.alpha * (reward + self.discount * futureQValue)
        
        # Update the Q-value for the state-action pair
        self.qValues[(state, action)] = updatedQValue

    def getPolicy(self, state):
        return self.computeActionFromQValues(state)

    def getValue(self, state):
        return self.computeValueFromQValues(state)


class PacmanQAgent(QLearningAgent):
    "Exactly the same as QLearningAgent, but with different default parameters"

    def __init__(self, epsilon=0.05,gamma=0.8,alpha=0.2, numTraining=0, **args):
        """
        These default parameters can be changed from the pacman.py command line.
        For example, to change the exploration rate, try:
            python pacman.py -p PacmanQLearningAgent -a epsilon=0.1

        alpha    - learning rate
        epsilon  - exploration rate
        gamma    - discount factor
        numTraining - number of training episodes, i.e. no learning after these many episodes
        """
        args['epsilon'] = epsilon
        args['gamma'] = gamma
        args['alpha'] = alpha
        args['numTraining'] = numTraining
        self.index = 0  # This is always Pacman
        QLearningAgent.__init__(self, **args)

    def getAction(self, state):
        """
        Simply calls the getAction method of QLearningAgent and then
        informs parent of action for Pacman.  Do not change or remove this
        method.
        """
        action = QLearningAgent.getAction(self,state)
        self.doAction(state,action)
        return action


class ApproximateQAgent(PacmanQAgent):
    """
       ApproximateQLearningAgent

       You should only have to overwrite getQValue
       and update.  All other QLearningAgent functions
       should work as is.
    """
    def __init__(self, extractor='IdentityExtractor', **args):
        """
        Initializes the Approximate Q-Learning agent with feature extractor and weights.
        """
        # Initialize feature extractor
        self.featExtractor = util.lookup(extractor, globals())()
        
        # Initialize the base QLearning agent
        PacmanQAgent.__init__(self, **args)
        
        # Initialize weights (to be learned)
        self.weights = util.Counter()

    def getWeights(self):
        """
        Returns the weights for the features.
        """
        return self.weights

    def getQValue(self, state, action):
        """
        Should return Q(state, action) = w * featureVector
        where * is the dot product operator.
        """
        # Get the features for the current (state, action) pair
        feature_vector = self.featExtractor.getFeatures(state, action)
        
        # Compute the dot product of weights and feature vector to get Q-value
        q_value = sum(self.weights[feature] * feature_vector[feature] for feature in feature_vector)
        
        return q_value

    def update(self, state, action, nextState, reward):
        """
        Updates the weights based on the transition using the Q-learning update rule.
        """
        # Compute the "difference" term
        difference = (reward + self.discount * self.getValue(nextState)) - self.getQValue(state, action)
        
        # Get the features of the current (state, action) pair
        feature_vector = self.featExtractor.getFeatures(state, action)
        
        # Update each weight based on the difference and the corresponding feature value
        for feature in feature_vector:
            self.weights[feature] += self.alpha * difference * feature_vector[feature]

    def final(self, state):
        """
        Called at the end of each game to perform any final actions.
        """
        # Call the base class final method
        PacmanQAgent.final(self, state)
        
        # Print out weights after training is complete (optional debugging)
        if self.episodesSoFar == self.numTraining:
            # You can print the learned weights here if desired
            # print(self.weights)
            pass
