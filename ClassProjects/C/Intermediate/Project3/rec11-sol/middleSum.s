	.text
	.global middleSum

middleSum:
	# Assume that RDI = x, RSI = y
  # Assume that y >= x and both are non-negative
	# Goal: Use a for loop to return the sum of all numbers between x and y inclusive.
	#       eg. if x is 3 and y is 6, then return the value 3 + 4 + 5 + 6
	#       eg. if x is 3 and y is 3, then return the value 3
  # 
	# ADD your code here
  movq $0, %rax     # sum = 0
  movq %rdi, %rcx   # i = x
top:
  addq %rcx, %rax   # sum += i
  incq %rcx         # i++
compare: 
  cmpq %rsi, %rcx   # Compare y to i
  jle top           # Jump if y <= i

	ret # Returns what is in RAX
	# END your code

