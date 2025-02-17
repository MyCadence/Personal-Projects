import unittest
import json
import sys
import os
from datetime import datetime

sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), "../AILoginAnalyzer"))) #import function testing

from ai_log_analyzer import convert_windows_time

class TestAIAnalyzer(unittest.TestCase):

    def test_convert_windows_time(self):
        timestamp = "/Date(1738955457686)" #parameter to test
        expected = datetime.utcfromtimestamp(1738955457686 / 1000).strftime('%Y-%m-%d %H:%M:%S')
        result = convert_windows_time(timestamp)
        self.assertEqual(result, expected)

        def test_json_parsing(self):
            """Test that the JSON file is parsed correct"""
            with open("../AIProjects/AILoginAnalyzer/failed_logins.json", "r", encoding="utf-16") as log_file:
                file_content = log_file.read()
                logs = json.loads(file_content) #Parse JSON data
                self.assertTrue(isinstance(logs, list)) #Ensure logs is a list

if __name__=="__main__":
    unittest.main()