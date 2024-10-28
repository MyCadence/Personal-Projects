$greeting = "Welcome to PowerShell"
Write-host $greeting

$numbers = @(1, 2, 3, 4, 5)
$sum = ($numbers | Measure-Object -Sum).Sum
Write-host "The sum is: $sum"