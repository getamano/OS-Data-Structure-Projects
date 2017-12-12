# Local Search Algorithm (LSA)

### Application for allocating courses to Semester using LSA

Write a program that uses Local Search as done in Section 4.8 of the Poole &amp; Mackworth textbook to
determine a course schedule that allows a student coming in with an AA degree in a completely non-
technical subject to complete their Computer Science degree at Metro State in n semesters.
For this program, I will describe the goal of the program, the (lack of) input, the rules of the system, your
choice of algorithms, the (short) report I’d like you to write, the output that I expect, and the grading rubric.

Goal:
Imagine that you have an AA degree in some subject that required you to take no math and no computer
courses, and you decide to come to Metro and get a Computer Science degree. It is possible to complete
a CS degree in seven semesters if you take 3 courses per semester (in fact, you will have two courses to
space, since there are 19 courses required for the degree).
Your goal is to use local search to come out with a schedule that has you completing the coursework in
seven semesters.

Input:
There is no actual “input” to this program. . The set of courses that you must take is given below, along
with the prerequisites of each course. Since you have an AA degree, you have met your General
Education (but not Liberal Studies) requirements. So you need to do Math, ICS, and two Liberal Studies
courses.

Rules to Complete CS Degree:
Courses:
For the purposes of this assignment, to complete a CS degree you must take the following courses. You
can hard-code this set of rules into your program:
Math: 120, 210, 215
ICS: 140, 141, 232, 240, 311, 340, 365, 372, 440, 460, 462, 490, 492, 499
Liberal Studies: 998, 999
Notes:
1. For a CS degree you need two electives, I am calling them ICS 490 and ICS 492 1
2. Since there are a bunch of Liberal Studies courses available, I just assume these generic
numbers “998” and “999”.
3. Since the numbers of the math, ICS, and liberal studies courses are disjoint, for the purposes of
this program it’s sufficient to refer to a course by its number without a subject designator, and
there will be no confusion.

Prerequisites:
Of course, to take a course, you need to take its prerequisites. This is what makes this assignment fun!
The prerequisites can also be hardcoded into your program. They are of two types:
n1 &lt; n2 means that course n1 must be taken before course n2.

1 Of course, as you probably know, you can also take any upper-division math course for an elective – but since you didn’t have
any math, and since all upper division math requires Calculus II, this would cost you an extra course. You probably also know
that you could do an internship instead of one of the elective courses, but this program doesn’t allow that. Finally, of course, you
can use other ICS courses for electives if you meet the prerequisites, such as ICS 325, 382, or 425.

n1 ≤ n2 means that course n1 must be taken before, or concurrently with, course n2.
Here are the prerequisites: You may embed this list in your program. (The order in which I list the
prerequisites isn’t important, the order is just the way I looked at them, basically math first, then ICS.)
 120 &lt; 210
 120 &lt; 215
 120 ≤ 140
 215 ≤ 141
 140 &lt; 141
 141 &lt; 232
 141 &lt; 240
 141 &lt; 311
 240 &lt; 311
 240 &lt; 340
 240 &lt; 365
 240 &lt; 372
 340 &lt; 440
 372 &lt; 499
 All seven courses at the 100- and 200-level (120, 210, 215, 140, 141, 232, 240) must be taken
before any course at the 400-level can be taken.
Additional Constraints:
 You may take no more than 3 courses per semester.
 499 must be taken in the final semester.
 Liberal Studies courses can be taken any time.
Semesters are numbered 1-7. Semesters 1, 4, and 7 are summers. That is important in the constraints
that follow. All courses are offered every semester except for the following:
 ICS 490 is never offered in the summer.
 ICS 492 is only offered in the summer.
Algorithm:
Start with a random assignment of courses to semesters, using the java Random class to select such a
random assignment. Then look for conflicts. If there are no conflicts, you are done. If there are conflicts,
then you should change the values of one or more courses following a Local Search strategy (or
combination of such strategies) from section 4.8.
You get to choose which strategy(ies) to follow. The only rules are that they need to be local search
strategies (no constraint satisfaction or anything like that), and they need to be done without any human
interaction. For instance, it’s always possible that you might get stuck and want to make a random move
of some sort to get the local search to progress. Your algorithm has to decide when to make such a
random move, and whether it should be a random walk or a random restart. Explain your rule for
randomness if you have one; maybe you use simulated annealing, maybe you use something else. But
explain it.
Basically, your algorithm should implement the local search meta-algorithm from Figure 4.6:

1: Procedure Local-Search(V,dom,C) 
2:           Inputs
3:                     V: a set of variables 
4:                     dom: a function such that dom(X) is the domain of variable X 
5:                     C: set of constraints to be satisfied 
6:           Output 
7:                     complete assignment that satisfies the constraints 
8:           Local
9:                     A[V] an array of values indexed by V 
10:           repeat
11:                     for each variable X do 
12:                               A[X] ←a random value in dom(X); 
13:                     while (stopping criterion not met &amp; A is not a satisfying assignment) 
14:                               Select a variable Y and a value V ∈dom(Y) 
15:                               Set A[Y] ←V 
16:                     if (A is a satisfying assignment) then 
17:                               return A 
18:           until termination
Output File
Offer the user two choices of output mode: Summary and Verbose.
In summary mode, print out the seven semesters to a file with the course numbers for a given semester
on each row. Here is one possible summary output:
Summer: 120 140
Autumn: 215 141 998
Spring: 240 232 311
Summer: 340 365 210
Autumn: 372 998 490
Spring: 440 460 462
Summer: 999 499 492

In verbose mode, print the proposed semester for each course on one line, with courses in numerical
order without regard for department. Each course should take up one digit for the semester and have
one space between courses. So it might look something like this, assuming a header line.
In this case, I chose in the first iteration to change the term of course 460, then in the second iteration I
chose to change 140, then in the third iteration I did a random restart.
You will need to write this into a text file, because there will probably be thousands of iterations of the
program.
120 140 141 210 215 232 240 311 340 365 372 440 460 462 490 492 499 998 999
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -
5 2 1 4 3 2 5 6 4 2 2 3 1 7 3 4 6 4 1
5 2 1 4 3 2 5 6 4 2 2 3 7 7 3 4 6 4 1
5 1 1 4 3 2 5 6 4 2 2 3 1 7 3 4 6 4 1
