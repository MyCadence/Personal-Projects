	.text
	.global power

power:

	# Assume that RDI = x and RSI = n
        # Goal: Compute x to the power of n
	# ADD your code here

  # // while(n) {
  # //   result *= x;
  # //   n--;

	movq	$1, %rax	  # result = 1

	jmp 	cond		    # while ...
loop:
	imulq	%rdi, %rax	# result = result * x
	decq	%rsi		    # n--

cond:
	// while (n > 0) ...
	cmpq	$0, %rsi  	# compare n to 0
	jg	loop		      # if n > 0, keep looping
	
end:
	ret               # Returns what is in RAX (result)
	# END your code
