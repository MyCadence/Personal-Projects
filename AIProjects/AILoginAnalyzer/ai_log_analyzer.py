import os
import openai
import json
from datetime import datetime

# Retrieve API key from environment variable
api_key = os.getenv("OPENAI_API_KEY")
openai.api_key = api_key

# Load JSON logs with utf-16 encoding to handle BOM markers
with open("failed_logins.json", "r", encoding="utf-16") as log_file:
    file_content = log_file.read()  # Read the content as a string
    print(file_content)  # Check the raw content

    logs = json.loads(file_content)  # Parse the JSON data

def convert_windows_time(timestamp):
    return datetime.utcfromtimestamp(int(timestamp) / 1000).strftime('%Y-%m-%d %H:%M:%S')

# Convert logs to a formatted message for AI processing
log_text = "\n".join(f"{convert_windows_time(log['TimeCreated'][6:-2])}: {log['Message']}" for log in logs)

# Query OpenAI GPT-4o for analysis
response = openai.chat.completions.create(
    model="gpt-4o",
    messages=[  # List of messages for the conversation
        {"role": "system", "content": "You are an AI log analyzer. Detect suspicious patterns in failed login attempts."},
        {"role": "user", "content": f"Analyze these login errors and report any suspicious activity:\n\n{log_text}"}
    ]
)

# Print AI analysis
print("AI Analysis:")
print(response.choices[0].message.content)