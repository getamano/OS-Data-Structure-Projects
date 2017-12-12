# Disk scheduling Replacement Algorithms

> A program that implements disk scheduling  replacement algorithms FIFO, LRU and Optimal

5 th homework assignment for operating systems (ICS 462) Disk Scheduling
Write a program that implements the following disk-scheduling algorithms from chapter 9 in
your text:

a. FCFS
b. SSTF
c. SCAN
d. C-SCAN
e. LOOK
f. C-LOOK

Your program will service a disk with n cylinders numbered 0 to n-1. The program will read
a data file (“Asg5Data.txt”) found in the assignment section which has two sets of
scheduling data. The first entry in the data file is the number of cylinders for your disk. The
next entry is the cylinder number the disk position at the beginning of the simulation. Next,
comes a line with a string of numbers representing the numbers of cylinders with I/O
requests and service them according to each of the algorithms listed above. Following that
is second set of this data in the file but with different values including a different number of
cylinders and a different start position. Your program will output the total amount of head
movement required by each algorithm. The output should consist of two sets of the
following:

For FCFS, the total head movement was xxx cylinders.
For SSTF, the total head movement was xxx cylinders.
For SCAN, the total head movement was xxx cylinders.
For C-SCAN, the total head movement was xxx cylinders.
For LOOK, the total head movement was xxx cylinders.
For C-LOOK, the total head movement was xxx cylinders.

Make certain to remove or turn off any debug output before you generate the output file.

Assume the disk will move from the smaller to larger cylinder numbers for the scan and look
algorithms. Do not forget to count the “back to the start” movement for the C-SCAN and C-
LOOK algorithms.

The data in this first set is that used on pages 447 – 451 in your text and may be used to check
your code. Then your program will read a second similar set of data from the same file and run
that. Note that the data second set has different number of cylinders.
Make comments - do any of the algorithms look especially good or bad? How many hours did
the assignment take? What did you learn from this assignment?
This assignment is worth 20 points.

This is due by noon the day after the final exam – November 29 th . As always, please put
comments at the top of your program with your name, the date, the assignment number, and a
brief description of the program. I must also see comments within the program. Also,
remember to put your name and the assignment number at the top of your comments and
output files.
e-mail your program, comments file, and output file to me. The comments should be in a
separate attached word or data file. The output should be in a separate attached data file.
