	.text
	.global collatzLength

collatzLength:

	# Assume that RDI = x
        # Goal: return the number of terms in the Collatz sequence from x.
        #       If x = 1 then done.
        #       Else if x is even then the next x is x/2.
        #       Else if x is odd then the next x is x*3 + 1.
	# ADD your code here

	movq	$1, %rax	# length = 1

	jmp	cond		# while ...
loop:
#	// if ((x & 1) == 0), then it must be even
	testq	$1, %rdi	# check (x & 1) to get the LSB of x.
	jne	else_odd	  # if (x & 1) != 0 then it must be odd (remember ne checks for ZF=0)
if_even:
	shrq	$1, %rdi	# x = (x >> 1) // This is the same as x/2
	jmp 	end_if
else_odd:
	leaq	1(%rdi, %rdi, 2), %rdi	# x = 2*x + x + 1 = 3*x + 1
end_if:
	incq	%rax		# length++
cond:
	// while (x != 1) ...
	cmp	$1, %rdi	# compare x to 1
	jne 	loop		# continue to loop while x != 1

end:
	ret # Returns what is in RAX
	# END your code
