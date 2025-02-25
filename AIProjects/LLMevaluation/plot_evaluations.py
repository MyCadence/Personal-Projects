import json
import matplotlib.pyplot as plt

# Load JSON data from a file
with open('evaluation_results.json', 'r') as file:
    data = json.load(file)

# Extract successful evaluations counts
app_success_counts = {app: len([test for test in tests if test["result"] == "success"]) 
                      for app, tests in data.items()}

# Plotting
plt.figure(figsize=(10, 6))
plt.bar(app_success_counts.keys(), app_success_counts.values(), color='skyblue')
plt.xlabel('Applications')
plt.ylabel('Number of Successful Evaluations')
plt.title('Successful Evaluations per Application')
plt.xticks(rotation=45, ha='right')
plt.tight_layout()

# Show plot
plt.show()