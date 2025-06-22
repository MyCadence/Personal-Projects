# analysis.py
# -----------
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


######################
# ANALYSIS QUESTIONS #
######################

# Set the given parameters to obtain the specified policies through
# value iteration.

def question2():
    answerDiscount = 0.9
    answerNoise = 0
    return answerDiscount, answerNoise

def question3a(): 
    """
    Prefer the close exit (+1), risking the cliff (-10)
    This means we should prioritize the close exit while risking the cliff.
    """
    answerDiscount = 0.1  # Small discount to prefer the close exit (+1)
    answerNoise = 0.0  # No noise, so the agent is not afraid of the cliff
    answerLivingReward = -1  # Mild penalty to encourage the agent to survive, but risk the cliff
    return answerDiscount, answerNoise, answerLivingReward
    # If not possible, return 'NOT POSSIBLE'


def question3b():
    """
    Prefer the close exit (+1), but avoiding the cliff (-10)
    This means we want the agent to go for the close exit while avoiding the cliff.
    """
    answerDiscount = 0.1  # Small discount to prefer the close exit (+1)
    answerNoise = 0.1  # Small noise to make the agent cautious and avoid the cliff
    answerLivingReward = -1  # Mild penalty to ensure the agent survives but avoids the cliff
    return answerDiscount, answerNoise, answerLivingReward
    # If not possible, return 'NOT POSSIBLE'


def question3c():
    """
    Prefer the distant exit (+10), risking the cliff (-10)
    The agent should prioritize the distant exit, even though the cliff is a risk.
    """
    answerDiscount = 1.0  # High discount to prefer the distant exit (+10)
    answerNoise = 0.0  # No noise, so the agent is not afraid of the cliff
    answerLivingReward = -1  # Mild penalty to encourage the agent to survive, even if risking the cliff
    return answerDiscount, answerNoise, answerLivingReward
    # If not possible, return 'NOT POSSIBLE'


def question3d():
    """
    Prefer the distant exit (+10), avoiding the cliff (-10)
    The agent should prioritize the distant exit and avoid the cliff.
    """
    answerDiscount = 1.0  # High discount to prioritize the distant exit (+10)
    answerNoise = 0.1  # Add noise to make the agent cautious of the cliff
    answerLivingReward = -1  # Mild penalty to encourage survival while avoiding the cliff
    return answerDiscount, answerNoise, answerLivingReward
    # If not possible, return 'NOT POSSIBLE'


def question3e():
    """
    Avoid both exits and the cliff (so an episode should never terminate)
    The agent should never terminate, meaning it should not go towards the exits or the cliff.
    """
    answerDiscount = 1.0  # No discount necessary since the agent should never prefer the exits
    answerNoise = 0.1  # Noise to avoid cliffs and exits
    answerLivingReward = 100  # High reward for living forever, preventing termination
    return answerDiscount, answerNoise, answerLivingReward
    # If not possible, return 'NOT POSSIBLE'

def question8():
    answerEpsilon = 0.1
    answerLearningRate = 0.3
    return answerEpsilon, answerLearningRate
    # If not possible, return 'NOT POSSIBLE'

if __name__ == '__main__':
    print('Answers to analysis questions:')
    import analysis
    for q in [q for q in dir(analysis) if q.startswith('question')]:
        response = getattr(analysis, q)()
        print('  Question %s:\t%s' % (q, str(response)))
