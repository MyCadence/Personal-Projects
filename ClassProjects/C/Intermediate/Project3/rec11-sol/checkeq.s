	.text
	.global checkeq

checkeq:
	# Assume that RDI = a and RSI = b
	# Goal: return 1 if a == b, or 0 otherwise
	# YOUR CODE HERE

#        // long result = 0;
  movq $0, %rax     # result = 0  (you can also do this with xorq)

#        // if (a == b) ...
	cmpq	%rsi, %rdi	# compare a to b
	jne	done		      # if (a != b), goto done to skip the increment
then:       
	incq	%rax		    # result = 1  (you can also just do this with movq)

done:
	ret               # returns what is in RAX
	# END YOUR CODE

## Alternate solution:
	# xorq	%rax, %rax	# result = 0, to ensure correct data size	
	# cmpq 	%rsi, %rdi	# compare a to b
	# sete	%al		# set al to true iff (a == b)
	# ret
