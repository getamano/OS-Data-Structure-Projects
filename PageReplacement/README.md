# Page Replacement Algorithms

### Operating System - A program that implements page replacement algorithms FIFO,  LRU and Optimal Using Java 

4th homework assignment for ICS 462 - Paging
Write a program that implements the FIFO and LRU (and optionally the Optimal) page replacement algorithms
presented in chapter 8 of your text. First generate a random page-reference string (this should be 20 entries long)
where page numbers range from 0 to 9. Apply the random page-reference string to each algorithm, and record the
number of page faults incurred by each algorithm. Implement the replacement algorithms so that the number of page
frames goes from 1 to 7 and you must compute the page fault for each of these frame numbers. Record the number of
page faults with each of these different page frames numbers and each of the different algorithms. Assume that
demand paging is used. Remember to count the first time a page comes in as this is a fault in demand paging.

Then do the same procedure except use the following page-reference string instead of the random one:
0,7,0,1,2,0,8,9,0,3,0,4,5,6,7,0,8,9,1,2

And then lastly do it with the page-reference string: 7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1

Note this last string is used for examples in your text on pages 387-390. This can be used to partially check your code.
Have separate clearly marked classes or methods for LRU and FIFO (and Optimal if you do it) replacement algorithms.
Also have comments within your code.
Make certain to have your name, date, assignment number, and a brief description of the program at the top of your
main method.
Your output should be in the following format (repeated 7 times, one for each number of page frames, and the set of
seven repeated 3 times, one for each data):

For x page frames, and using string page reference string xxxxxxxxxxxxxxxxxx:
   FIFO had ### page faults.
   LRU had ### page faults.
   Optimal had ### page faults.
   
Where ### is the number of page faults and x is the number of page frames
Remember to remove or turn off all of your debug output before you generate the output to send to me.
Your name and the assignment number must be placed at the top your output file by your program.
Send me an e-mail (Philip.lamb@metrostate.edu) with a copy of your source code, output file from your run, and your
comments file.

Place the output in a separate attached text file, do not use screen shots.
Place your comments in another attached text or word file and make certain that your name and the assignment
number are at the top of your comments file. Remember to place the approximate number of hours you spent on this
assignment in your comments file.

This problem is worth 15 points or 20 if you do the optional part.
This assignment is due by noon Nov 22, 2017.
Note the Optimal algorithm is much harder than either of the other two.

Look for errors:
  - More faults than items – not clearing a counter?
  - No faults – initial fault at least.
  - Hand check your results.
  - Compare with the results in the book.
