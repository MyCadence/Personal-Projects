$logData = Get-WinEvent -LogName Security -MaxEvents 10000 | Where-Object { $_.Id -eq 4625 } #Recently Failed Login Attempts
$selectedData = $logData | Select-Object TimeCreated, Message
$logJson = $selectedData | ConvertTo-Json -Depth 2 #Convert to JSON format with depth 2
Write-Output $logJson
$logJson | Out-File "failed_logins.json"