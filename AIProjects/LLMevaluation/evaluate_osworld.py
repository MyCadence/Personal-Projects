import json
import openai
import time
import os

# Load API Key (Make sure to replace 'your-api-key' with your actual key)
# Retrieve API key from environment variable
api_key = os.getenv("OPENAI_API_KEY")
openai.api_key = api_key

# Load OSWorld test dataset
def load_dataset(file_path):
    with open(file_path, "r", encoding="utf-8") as f:
        return json.load(f)

# Function to evaluate a prompt with GPT-4o Mini
def evaluate_prompt(prompt):
    try:
        start_time = time.time()
        response = openai.ChatCompletion.create(
            model="gpt-4o-mini",
            messages=[{"role": "user", "content": prompt}]
        )
        elapsed_time = time.time() - start_time
        return response["choices"][0]["message"]["content"], elapsed_time
    except Exception as e:
        return str(e), None

# Evaluate dataset
def evaluate_dataset(dataset_path):
    with open(dataset_path, "r", encoding="utf-8") as f:
        dataset = json.load(f)  # Load as a dictionary

    results = {}
    for category, test_ids in dataset.items():
        results[category] = []
        for test_id in test_ids:
            # Process the test_id (UUID)
            evaluation_result = run_evaluation(test_id)  # Replace with actual evaluation function
            results[category].append({"test_id": test_id, "result": evaluation_result})

    return results

def run_evaluation(test_id):
    # Placeholder for actual model evaluation
    return "success"  # Replace with actual logic

def main():
    dataset_path = "test_small.json"
    results = evaluate_dataset(dataset_path)

    # Save results to a file
    with open("evaluation_results.json", "w", encoding="utf-8") as f:
        json.dump(results, f, indent=4)

    print("Evaluation completed. Results saved to evaluation_results.json.")

if __name__ == "__main__":
    main()