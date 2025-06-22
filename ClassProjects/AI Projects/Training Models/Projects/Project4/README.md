# Homework 4 - Machine Learning

## Requirements

- Python 3.8+
- Libraries:
  - `numpy==1.23.1`
  - `matplotlib==3.5.2`

## Installation

1. Clone the repository or download the code files.
2. Install the required libraries by running the following in your terminal:
   ```bash
   pip install numpy matplotlib

## Testing Question 3 A Specifically
1. Replace the default rff_model_sample function with the stub code below.

def rff_model_sample(Theta, Omega, B, model_X):
    sampled_X = np.linspace(model_X.min(axis=0), model_X.max(axis=0), 100)
    Phi = apply_RFF_transform(sampled_X, Omega, B)
    
    # Add a column of ones for the bias term (intercept)
    Phi = np.hstack([np.ones((Phi.shape[0], 1)), Phi])  # Add ones to Phi for the bias term
    
    # Now we can safely perform the matrix multiplication
    sampled_Y = Phi.dot(Theta)
    return sampled_X, sampled_Y

2. Remember to revert back to its original state before testing questions 1 and 2.