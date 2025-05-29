	.text
	.global countPos

countPos:

	# Assume that RDI = arr (address), RSI = n (number of elements)
	# Goal: return the number of elements in arr with a positive value
	# ADD your code here

  # while(index < n) {
  #   if(arr[index] > 0) {
  #     count++;
  #   }
  #   index++;

	movq  $0, %rax		# count = 0 (can also be done with xorq for speed efficiency,  see below)
	xorq	%rcx, %rcx	# index = 0 (can also be done with movq for coding simplicity, see above)
	jmp 	cond			  # while...
loop:
	# if (arr[index] > 0) ... // A + i * k = arr + index * 8
	cmpq	$0, (%rdi, %rcx, 8)	# compare arr[index] with 0
	jle	after_if	# if not (arr[index] > 0), skip
positive:
	incq	%rax			  # count++ // value was positive
after_if:
	incq	%rcx			  # index++
cond:
	// while (index < n) ...
	cmpq	%rsi, %rcx	# compare index to n
	jl	loop			    # loop while index < n
		
	ret # Returns what is in RAX (count)
	# END your code
