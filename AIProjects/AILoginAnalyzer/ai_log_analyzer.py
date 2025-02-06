import openai
import json

# Set your OpenAI API key
openai.api_key = "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"
# Function to process logs and analyze them using GPT-4o
def analyze_logs(log_data):
    # Example prompt to analyze logs using GPT-4o
    prompt = f"Analyze these security logs and provide insights on potential security risks:\n{log_data}"
    
    # Make the API request to OpenAI with GPT-4o
    response = openai.Completion.create(
        engine="gpt-4o",  # Specify GPT-4o engine
        prompt=prompt,
        max_tokens=150,  # Limit the length of the output
        temperature=0.7,  # Controls randomness of responses
    )

    return response.choices[0].text.strip()

# Example: Load the log data (from JSON, PowerShell output, or file)
log_json = '{"TimeCreated": "2025-02-06 10:00:00", "Message": "Failed login attempt by user Alice"}'
log_data = json.loads(log_json)

# Analyze the log data
insights = analyze_logs(log_data)

# Output the results
print("AI Analysis Results:")
print(insights)